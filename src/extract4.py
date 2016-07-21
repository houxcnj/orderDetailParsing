import json
import os.path
import datetime



def createOrder(lines):

    order = {}
    order['orderID'] = ""
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
    order['refund'] = "$0.00"
    order['phone'] = ""


    for index, line in enumerate(lines):
        # print "..................",index, line

        try:
            if line.startswith("Order ID"):
                orderId = line.split("#")
                order['orderID'] = orderId[-1]

            elif line.startswith( 'Ship to'):
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

            elif line.startswith('Phone'):
                phone = line.split(":")
                order['phone'] = phone[-1]


            elif line.startswith('Purchase date'):
                words = line.split("\t")
                if len(words) > 1:
                    d =" ".join(words[1].split()[1:4])
                    order['purchase_date'] = datetime.datetime.strptime(d, '%b %d, %Y').strftime('%Y-%m-%d')
                    # print order['purchase_date']
                else:
                    words = line.split()
                    d = " ".join(words[3:6])
                    order['purchase_date'] = datetime.datetime.strptime(d, '%b %d, %Y').strftime('%Y-%m-%d')
                #print "dates ", words


            elif line.startswith( 'Refund total'):
                refundAmount = line.split()
                order['refund'] = refundAmount[-1]
                #print "refund: ", order['refund']


            elif lines[index+1].startswith( 'SKU:'):
                sku_words = lines[index+1].split(":")
                sku = sku_words[1].split("_")
                order['sku'] = sku[0]
                order['product']  = line
                #print "information: ",order['information']
                #print "SKU: ",order['sku']

            elif line.startswith( 'ASIN:'):
                asin_words = line.split(":")
                order['asin'] = asin_words[1]
                #print "ASIN: ", order['asin']

        except IndexError:
            pass




    return order

# filename = raw_input("Enter file:")
# if len(filename)<1 :
filename = "test.txt"
#hand = open(filename, "r")
#lines = [line.strip() for line in hand.readlines()]
#createOrder(lines)
#hand.close()
with open('data.json', 'a') as fp:
    hand = open(filename, "r")
    lines = [line.strip() for line in hand.readlines()]
    #print lines
    error = False
    order = createOrder(lines)
    hand.close()
    count = 0
    for k, v in order.items():
        print k, ": ", v
        if not v:
            count += 1

    if count < 3:
        message = "Order ID: " + order['orderID'] + "\n"
        message += "Name: " + order['name'] + "\n"
        message += "Address 1:" + order['address1'] +"\n"
        message += "Address 2:" + order['address2'] + "\n"
        message += "City: " + order['city'] + "\n"
        message += "State:" + order['state'] + "\n"
        message += "Zip Code1: " + order['zipcode1'] + "\n"
        message += "Zip Code2: " + order['zipcode2'] + "\n"
        message += "Prduct: " + order['product'] + "\n"
        message += "SKU: " + order['sku'] + "\n"
        message += "ASIN: " + order['asin'] + "\n"
        message += "Purchase Date: " + order['purchase_date'] + "\n"
        message += "Refund: " + order['refund']

    else:
        print "Sorry, please input again!"
        error = True
    json.dump(order, fp, indent = 2)
    res = json.dumps(order)
    print " "
    print "..........................."
    print " "

fp.close()
