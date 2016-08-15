import os
import json

def extractTracking(file):

	f = open(file, "r")

	lines = f.readlines()

	res = []

	for line in lines:
		info = line.split("\t")
		thisLine = (info[0], [info[-6], info[-5]])
		res.append(thisLine)

	f.close()

	return dict(res)

def toJson():
	f = open("tracking.json", "w")
	tracking = extractAll()
	json.dump(tracking, f, indent = 2, ensure_ascii = False)
	f.close()

def extractAll():
	res = {}
	dir_ = os.path.dirname(__file__)
	dir_ = os.path.abspath(os.path.join(dir_, os.pardir))
	trackingDir =  os.path.join(dir_ , 'Access\\')
	for file in os.listdir(trackingDir):
		if file.endswith(".txt"):
			res.update(extractTracking(trackingDir+file))

	return res

toJson()
