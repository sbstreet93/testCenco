import json
import boto3
import pymysql
import pandas as pd
import io

s3 = boto3.client("s3")
def read_data_from_s3(event):
    
    if event:
        s3_records = event["Records"][0]
        bucket_name = str(s3_records["s3"]["bucket"]["name"])
        file_name = str(s3_records["s3"]["object"]["key"])
        file_obj = s3.get_object(Bucket=bucket_name, Key=file_name)

        file_content = file_obj["Body"].read()
        read_excel_data = io.BytesIO(file_content)
        df = pd.read_excel(read_excel_data, usecols=['Mail', 'Store', 'Amount'])
    
    return df.to_json(orient='index')

def getCliId(mail, conn):
    try:
        idCli = 0
        sql_select_Query = "select id from client where mail = '"+str(mail)+"'"
        cursor = conn.cursor()
        cursor.execute(sql_select_Query)
        records = cursor.fetchall()
        for row in records:
             idCli = row[0]
    except:
        print("Error reading data from MySQL table getCliId")
    return idCli
def getStoreId(store, conn): 
    try:
        idStore = 0
        sql_select_Query = "select id from store where store_name = '"+str(store)+"'"
        cursor = conn.cursor()
        cursor.execute(sql_select_Query)
        records = cursor.fetchall()
        for row in records:
             idStore = row[0]
    except:
        print("Error reading data from MySQL table getStoreId")
    return idStore
def getAmountStoreCli(cliId, storeId, conn):
    amount = -1
    try:
        sql_select_Query = "select amount from store_client where id_store = "+str(storeId)+" and id_client=" + str(cliId)
        cursor = conn.cursor()
        cursor.execute(sql_select_Query)
        records = cursor.fetchall()
        for row in records:
             amount = row[0]
    except Exception as e:
        print(e)
    return amount
def add(cliId, storeId, amount, conn):
    try:
        preAmount = getAmountStoreCli(cliId, storeId, conn)
        if(preAmount == -1):
            print("add " + str(cliId))
            sql_insert_query = "insert into store_client (id_store, id_client, amount) values("+str(storeId)+"," + str(cliId) + "," + str(amount) +")"
            cursor = conn.cursor()
            cursor.execute(sql_insert_query)
            conn.commit()
        else:
            print("update " + str(cliId))
            amount = amount + preAmount
            sql_update_query = "update store_client set amount="+str(amount) +" where id_store=" + str(storeId) + " and id_client=" + str(cliId)
            cursor = conn.cursor()
            cursor.execute(sql_update_query)
    except Exception as e:
        print(e)
    
def discount(cliId, storeId, amount, conn):
    try:
        preAmount = getAmountStoreCli(cliId, storeId, conn)
        if(preAmount == -1):
            print("no existe relacion")
            return
        elif(preAmount < amount):
            print("monto a descontar excede maximo")
            return
        else:
            amount = preAmount - amount
            sql_update_query = "update store_client set amount="+str(amount) +" where id_store=" + str(storeId) + " and id_client=" + str(cliId)
            cursor = conn.cursor()
            cursor.execute(sql_update_query)
    except:
        print("Error discount")
            
def lambda_handler(event, context):
    rds_endpoint  = "testcenco.chn3mgxbd6nq.us-east-2.rds.amazonaws.com"
    username = "root"
    password = "password"
    db_name = "testCenco"
    conn = None
    try:
        conn = pymysql.connect(rds_endpoint, user=username, passwd=password, db=db_name, connect_timeout=5)
    except pymysql.MySQLError as e:
        print("ERROR: Unexpected error: Could not connect to MySQL instance.")
    jsonStr = read_data_from_s3(event)
    jsonObj = json.loads(jsonStr)
    with conn.cursor() as cur:
        for key in jsonObj:
            mail = jsonObj[key]['Mail']
            store = jsonObj[key]['Store']
            amount = jsonObj[key]['Amount']
            print(mail)
            cliId = getCliId(mail, conn)
            storeId = getStoreId(store, conn)
            if(cliId == 0):
                print("cliente no existe")
                continue
            if(storeId == 0):
                print("tienda no existe")
                continue
            try:
                if amount > 0:
                    add(cliId, storeId, amount, conn)
                else:
                    amount = amount*-1
                    discount(cliId, storeId, amount, conn)
            except:
                continue
    if conn:
        conn.commit()
    conn.close()