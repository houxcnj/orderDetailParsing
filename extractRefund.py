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
			if l[13] in lineDict:
				l[13] = l[13] + "2"
			lineDict[l[13]] = l[14]
				
	return refundDict
"""
def orderInfo(rfile, lines):
	f = open(rfile, "r")
	lines2 = f.readlines()
	f.close()	

	orderNo = []
	for line in lines:
		l = line.split("\t")
		# print l[7], l[8]
		orderNo.append(l[7])

	res = []
	lines2[:] = [x for x in lines2 if "Order" in x]
	for order in set(orderNo):
		print order
		for line in lines2:
			if order in line:
				res.append(line)
	return res
"""
def getOriOd(lines, refundDict):
	if not lines:
		return None
	lineDict = {}
	for line in lines:
		l = line.split("\t")
		l[13] = "Ori_" + l[13]
		if l[7] in refundDict:
			if l[13] in refundDict[l[7]]:
				l[13] = l[13] + "2"
			lineDict = {l[13]:l[14]}
			refundDict[l[7]].update(lineDict)
			
	return refundDict


def orderInfo(rfile, refundDict):
	f = open(rfile, "r")
	lines = f.readlines()
	f.close()

	lines[:] = [x for x in lines if "Order" in x]
	for line in lines:
		l = line.split("\t")
		if not l[7] in refundDict:
			lines.remove(line)

	return lines

def refundInfo(rfile):
	f = open(rfile, "r")
	lines = f.readlines()
	f.close()

	lines[:] = [x for x in lines if "Refund" in x]

	return lines

def toJson():
	refundDict = extractRefOds()
	f = open("refund.json", "w")
	json.dump(refundDict, f, indent = 2, ensure_ascii = False)
	f.close()

def extractRefOds():
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
			if not lines:
				continue			
			res.update(getRefund(lines))

	for file in os.listdir(refundDir):
		if file.endswith(".txt"):
			lines = orderInfo(refundDir + file, res)
			if not lines:
				continue
			res.update(getOriOd(lines, res))

	return res

toJson()