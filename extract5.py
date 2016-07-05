import re
import json
import os.path

def createOrder(lines):

    order = {}
    order['name']  = ""
    order['address1'] = ""
    order['address2'] = ""
    order['state'] = ""
    order['city'] = ""
    order['zipcode1'] = ""
    order['zipcode2'] = ""
    order['product'] = ""
    order['sku'] = ""
    order['asin'] = ""
    order['purchase_date'] = ""
    order['refund'] = False


    for index, line in enumerate(lines):
        #print "..................",index, line

        try:

            if line.startswith( 'Ship to'):
                name_words = line.split()
                order['name']  = " ".join(name_words[2:])

                address = ""
                for i in range(3):
                    if not lines[index + i+1].startswith('Phone'):
                        address = address + "," + lines[index + i+1]
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


            elif line.startswith('Purchase date'):
                words = line.split("\t")
                if len(words) > 1:
                    order['purchase_date'] = words[1]
                else:
                    words = line.split(" ")
                    order['purchase_date'] = " ".join(words[2:])
                #print "dates ", words


            elif line.startswith( 'Refund'):
                order['refund'] = True
                #print "refund: ", order['refund']


            elif lines[index+1].startswith( 'SKU:'):
                sku_words = lines[index+1].split(":")
                order['sku'] = sku_words[1]
                order['product']  = line
                #print "information: ",order['information']
                #print "SKU: ",order['sku']

            elif line.startswith( 'ASIN:'):
                asin_words = line.split(":")
                order['asin'] = asin_words[1]
                #print "ASIN: ", order['asin']

        except IndexError:
            pass


    for k, v in order.items():
        print k, ": ", v

    return order

namefile = open("name.txt")
print "namefile opened"
namelist = [line.strip() for line in namefile.readlines()]
with open('data.json', 'a') as fp:
    for name in namelist:
        filename = name+".txt"
        print filename
        hand = open(filename, "r")
        lines = [line.strip() for line in hand.readlines()]
        #print lines
        order = createOrder(lines)
        hand.close()
        json.dump(order, fp, indent = 2)
        print " "
        print "..........................."
        print " "

namefile.close()
fp.close()
