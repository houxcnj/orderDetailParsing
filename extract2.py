import re
import json
# import csv

f = raw_input("Enter file:")
if len(f)<1 : f = "test.txt"
hand = open(f, "r")
order = {}
order['name']  = ""
order['address1'] = ""
order['address2'] = ""
order['city'] = ""
order['state'] = ""
order['zipcode1'] = ""
order['zipcode2'] = ""
order['product'] = ""
order['sku'] = ""
order['asin'] = ""

for line in hand:
    line = line.rstrip()
    if re.search('Ship to', line):
        name_words = line.split()
        order['name']  = " ".join(name_words[2:])
        print "name: ",order['name']
        address = ""
        for i in range(3):
            line = hand.next()
            if not "Phone" in line:
                address = address + "," + line.strip()
        address_words = address.split(",")
        order['address1'] = address_words[1]
        if len(address_words) > 4:
            order['address2'] = address_words[2]
        order['city'] = address_words[-2]
        state_zipcode = address_words[-1].split()
        order['state'] = state_zipcode[0]
        zipcode = state_zipcode[1].split("-")
        order['zipcode1'] = zipcode[0]
        if len(zipcode) > 1:
            order['zipcode2'] = zipcode[-1]

        print "address1: ", order['address1']
        print "address2: ", order['address2']
        print "city: ", order['city']
        print "state: ", order['state']
        print "zipcode1: ",order['zipcode1']
        print "zipcode2: ", order['zipcode2']
        continue

    nextline = next(hand, "")
    if re.search('SKU:', nextline):
        sku_words = nextline.strip().split(":")
        order['sku'] = sku_words[1]
        order['product']  = line
        print "product: ",order['product']
        print "SKU: ",order['sku']

    if re.search("ASIN:", line):
        asin_words = line.split(":")
        order['asin'] = asin_words[1]
        print "ASIN: ", order['asin']
hand.close()

with open('data.json', 'a') as fp:
    json.dump(order, fp, indent = 2, sort_keys = True)
fp.close()

"""
writer = csv.writer(open('result.csv', 'a'))
for key, value in order.items():
   writer.writerow([key, value])
writer.writerow([])
"""