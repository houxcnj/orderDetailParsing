import re
import json

f = raw_input("Enter file:")
if len(f)<1 : f = "test.txt"
hand = open(f, "r")

order = {}
order['name']  = "NA"
order['address'] = "NA"
order['state'] = "NA"
order['zipcode'] = "NA"
order['information'] = "NA"
order['sku'] = "NA"
order['asin'] = "NA"

for line in hand:
    line = line.rstrip()
    if re.search('Ship to', line):
        name_words = line.split()
        order['name']  = " ".join(name_words[2:])
        print "name: ",order['name']
        for i in range(2):
            order['address'] += hand.next().strip()
        address_words = order['address'].split()
        order['state'] = address_words[-2]
        order['zipcode'] = address_words[-1]
        print "address: ", order['address']
        print "state: ", order['state']
        print "zipcode: ",order['zipcode']
        continue


    nextline = next(hand, "")
    if re.search('SKU:', nextline):
        sku_words = nextline.strip().split(":")
        order['sku'] = sku_words[1]
        order['information']  = line
        print "information: ",order['information']
        print "SKU: ",order['sku']

    if re.search("ASIN:", line):
        asin_words = line.split(":")
        order['asin'] = asin_words[1]
        print "ASIN: ", order['asin']

with open('data.json', 'a') as fp:
    json.dump(order, fp, indent = 2)
fp.close()

#with open('output.json', 'r') as out :
#    data = json.load(out)

#data.update(order)

#with open('output.json', 'w') as out:
#    json.dump(data, out)
