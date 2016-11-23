import json
import os
import sys

# get the refund order's information.
def getRefund(lines):
	if not lines:
		return None
	refundDict = {}
	lineDict = {}
	for line in lines:
		l = line.split("\t")
		if not l[7] in refundDict:
			# lineDict.clear()
			lineDict = {l[13]:l[14]}
			refundDict[l[7]] = lineDict
		else:
			lineDict[l[13]] = l[14]
	return refundDict

def orderInfo(rfile, lines):
	f = open(rfile, "r")
	lines2 = f.readlines()
	f.close()	

	orderNo = []
	for line in lines:
		l = line.split("\t")
		orderNo.append(l[7])

	res = []
	for order in set(orderNo):
		for line in lines2:
			thisLine = line.split("\t")
			if order == thisLine[8] and "Order" in line:
				
				thisLine[13] = "Original_"+thisLine[13]
				res.append("\t".join(thisLine))
	return res


def refundInfo(rfile):
	f = open(rfile, "r")
	lines = f.readlines()
	f.close()

	lines[:] = [x for x in lines if "Refund" in x]

	return lines

def toJson():
	refundDict = extractAll()
	f = open("refund.json", "w")
	json.dump(refundDict, f, indent = 2, ensure_ascii = False)
	f.close()

def extractAll():
	res = {}
	if getattr(sys, 'frozen', False):
            dir_ = os.path.dirname(sys.executable)
        else:
            # unfrozen
            dir_ = os.path.dirname(os.path.realpath(__file__))
        dir_ = os.path.abspath(os.path.join(dir_, os.pardir))
	refundDir =  os.path.join(dir_, 'Report\\')
	for file in os.listdir(refundDir):
		if file.endswith(".txt"):
			lines = refundInfo(refundDir+file)
			lines += orderInfo(refundDir+file, lines)
			if not lines:
				continue			
			res.update(getRefund(lines))

	return res

toJson()