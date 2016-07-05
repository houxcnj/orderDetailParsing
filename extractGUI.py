from Tkinter import *
import tkMessageBox

class Application(Frame):
    def createOrder(self):
        lines = self.Input.get("1.0", "end-1c")
        lines = lines.encode("utf-8").split("\n")
        print lines
        
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
        order['refund'] = False


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



                elif line.startswith('Purchase date'):
                    words = line.split("\t")
                    if len(words) > 1:
                        order['purchase_date'] = words[1]
                    else:
                        words = line.split(" ")
                        order['purchase_date'] = " ".join(words[2:])
                    #print "dates ", words


                elif line.startswith( 'Refund total'):
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

        # return order
        tkMessageBox.showinfo("Information", order)

    def clear_text(self):
        self.Input.delete('1.0', END)


    def createWidgets(self):
        """
        self.QUIT = Button(self)
        self.QUIT["text"] = "QUIT"
        self.QUIT["fg"]   = "red"
        self.QUIT["command"] =  self.quit

        self.QUIT.pack({"side": "left"})
        """

        self.Input = Text(self, height=50, width=60)
        self.Input.pack()
        self.runButton = Button(self, text = "Run", command = self.createOrder)
        self.runButton.pack()

        self.clearButton = Button(self, text = "Clear All", command = self.clear_text)
        self.clearButton.pack()


    def __init__(self, master=None):
        Frame.__init__(self, master)
        self.pack()
        self.createWidgets()

root = Tk()
app = Application(master=root)
app.mainloop()
root.destroy()
