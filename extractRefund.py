import json

def getRefund(lines):
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


def refundInfo():
	f = open("C:\\Users\\unu\\Documents\\pythonwork\\report.txt", "r")
	lines = f.readlines()
	f.close()

	lines[:] = [x for x in lines if "Refund" in x]

	return lines

def toJson():
	lines = refundInfo()
	refundDict = getRefund(lines)
	f = open("refund.json", "w")
	json.dump(refundDict, f, indent = 2, ensure_ascii = False)
	f.close()

toJson()