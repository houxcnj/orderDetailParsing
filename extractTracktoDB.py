import os
import json
import MySQLdb as my

def extractTracking(file):

	f = open(file, "r")

	lines = f.readlines()
	lines = lines[1:]

	db = my.connect(host = "",
		user = "",
		passwd = "",
		db = "")

	cursor = db.cursor()

	query = "INSERT IGNORE INTO TrackingInfo VALUES(%s,%s,%s);"

	for line in lines:
		info = line.split("\t")
		thisLine = (info[0], [info[-6], info[-5]])
		values = (info[0], info[-6], info[-5])
		print values
		cursor.execute(query, values)
		
		db.commit()

	db.close()

	f.close()

def extractAll():
	res = {}
	dir_ = os.path.dirname(__file__)
	dir_ = os.path.abspath(os.path.join(dir_, os.pardir))
	trackingDir =  os.path.join(dir_ , 'Access\\')
	for file in os.listdir(trackingDir):
		if file.endswith(".txt"):
			extractTracking(trackingDir+file)

	return res

extractAll()
