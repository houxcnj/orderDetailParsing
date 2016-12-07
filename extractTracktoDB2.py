import os
import json
import MySQLdb as my


def extractTracking(file):

	f = open(file, "r")

	lines = f.readlines()

	res = []

	for line in lines:
		info = line.split("\t")
		# info[6] = datetime.datetime.strptime(info[6], '%b %d, %Y').strftime('%Y-%m-%d')
		info[6] = info[6][:10]
		thisLine = (info[0], [info[-6], info[-5], info[6]])
		res.append(thisLine)

	f.close()

	return dict(res)

def toDB(trackingDict):

	db = my.connect(host = "",
		user = "",
		passwd = "",
		db = "")

	cursor = db.cursor()

	query = "INSERT INTO TrackingInfo VALUES(%s,%s,%s,%s);"

	for key, value in trackingDict.items():
		values = (key, value[0], value[1], value[2])
		print values
		cursor.execute(query, values)
		
		db.commit()

	db.close()


def extractAll():
	res = {}
	dir_ = os.path.dirname(__file__)
	dir_ = os.path.abspath(os.path.join(dir_, os.pardir))
	trackingDir =  os.path.join(dir_ , 'Access\\')
	for file in os.listdir(trackingDir):
		if file.endswith(".txt"):
			toDB(extractTracking(trackingDir+file))

	return res

extractAll()
