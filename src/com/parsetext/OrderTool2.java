package com.parsetext;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.MessageDigest;
import java.time.LocalDateTime;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.zendesk.client.v2.Zendesk;
import org.zendesk.client.v2.model.CustomFieldValue;
import org.zendesk.client.v2.model.Ticket;

import com.amazonservices.mws.orders._2013_09_01.MarketplaceWebServiceOrders;
import com.amazonservices.mws.orders._2013_09_01.MarketplaceWebServiceOrdersClient;
import com.amazonservices.mws.orders._2013_09_01.MarketplaceWebServiceOrdersException;
import com.amazonservices.mws.orders._2013_09_01.model.GetOrderRequest;
import com.amazonservices.mws.orders._2013_09_01.model.GetOrderResponse;
import com.amazonservices.mws.orders._2013_09_01.model.ListOrderItemsRequest;
import com.amazonservices.mws.orders._2013_09_01.model.ListOrderItemsResponse;
import com.amazonservices.mws.orders._2013_09_01.model.ResponseHeaderMetadata;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import javax.swing.JFormattedTextField;
 

public class OrderTool2 extends JFrame {

	private JPanel contentPane;
	private static JTextArea logArea; 
	private JTextField orderField;
	private JTextField skuField;
	private JTextField asinField;
	private JTextField nameField;
	private JTextField add1Field;
	private JTextField add2Field;
	private JTextField cityField;
	private JTextField stateField;
	private JTextField zip1Field;
	private JTextField zip2Field;
	private JTextField phoneField;
	private JTextField replaceStdField;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField productField;
	private JFormattedTextField refundField;
	private final Action action = new SwingAction();
	private final Action action_1 = new SwingAction_1();
	private final Action action_2 = new SwingAction_2();
	private String orderID;
	private String fullName;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String zipcode1;
	private String zipcode2;
	private String purchaseDate;
	private String sku;
	private String asin;
	private double refund;
	private String product;
	private String phone;
	private String fulChannel;
	private String channel;
	private String email;
	private String ssku;
	private double oriPrice;
	private double rfPrice;
	private double shipping;
	private double rfShipping;
	private double promotion;
	private double rfPromotion;
	private double tax;
	private double rfTax;
	private JTextField dateField;
	private final Action action_3 = new SwingAction_3();
	private JTextField ticketNField;
	private Ticket ticket_bak;
	private Ticket ticket;
	private final Action action_4 = new SwingAction_4();
	private String loginID;
	private String passwd;
	private String brand;
	
	private static JSONObject fieldData;
	private JTextField idInputField;
	private JTextField ffField;
	private JTextField channelField;
	private JTextField sskuField;
	private JTextField emailField;
	
	private String sellerId;
	private JTextField crField;
	private JTextField trackField;
	private JFormattedTextField rfpField;
	private JFormattedTextField rfsField;
	private JFormattedTextField ppriceField;
	private JFormattedTextField shippingField;

	private URI uri;

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderTool2 frame = new OrderTool2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/
	/**
	 * Create the frame.
	 * @throws URISyntaxException 
	 */
	public OrderTool2() throws URISyntaxException {
		
		System.out.println(sellerId);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUnuCustomerSupport = new JLabel("UNU Customer Support Tool Kit");
		lblUnuCustomerSupport.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblUnuCustomerSupport.setBounds(270, 11, 309, 25);
		lblUnuCustomerSupport.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblUnuCustomerSupport);
		
		// create process button and add the process action
		JButton btnProcess = new JButton("Process");
		//btnNewButton.setAction(action);
		
		btnProcess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearTextFields();
				logArea.append(LocalDateTime.now().toString() + ": Processing\n");
				// get input from idInputField
				orderID = idInputField.getText();
				MarketplaceWebServiceOrdersClient client = MWSConfig.getClient();
				
				try {
					GetOrderRequest request = new GetOrderRequest();
		        	request.setSellerId(sellerId);
		        	String mwsAuthToken = "";
		        	request.setMWSAuthToken(mwsAuthToken);
		        	List<String> amazonOrderId = new ArrayList<String>();
		        	amazonOrderId.add(orderID);
		        	request.setAmazonOrderId(amazonOrderId);
		        
		        	String orderInfo = invokeGetOrder(client, request);
		        	DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		        	InputSource src = new InputSource();
		        	src.setCharacterStream(new StringReader(orderInfo));
		        	Document doc = builder.parse(src);
		        	
		        	// get value from each key in xml string. 
		        	orderID = doc.getElementsByTagName("AmazonOrderId").item(0).getTextContent();
		        	fulChannel = doc.getElementsByTagName("FulfillmentChannel").item(0).getTextContent();
		        	if (fulChannel.equals("AFN")) {
		        		fulChannel = "Amazon";
		        	}
		        	else {
		        		fulChannel = "Seller";
		        	}
		        	channel = doc.getElementsByTagName("SalesChannel").item(0).getTextContent();
					fullName = doc.getElementsByTagName("Name").item(0).getTextContent();
					address1 = doc.getElementsByTagName("AddressLine1").item(0).getTextContent();
					try {
					address2 = doc.getElementsByTagName("AddressLine2").item(0).getTextContent();
					} catch (Exception e2) {
						address2 = "";
					}
					city = doc.getElementsByTagName("City").item(0).getTextContent();
					state = doc.getElementsByTagName("StateOrRegion").item(0).getTextContent();
					zipcode1 = doc.getElementsByTagName("PostalCode").item(0).getTextContent();
					if (zipcode1.length() > 5) {
						zipcode2 = zipcode1.substring(6);
						zipcode1 = zipcode1.substring(0, 5);
					}
					else {
						zipcode2 = "";
					}
					purchaseDate = doc.getElementsByTagName("PurchaseDate").item(0).getTextContent();
					purchaseDate = purchaseDate.substring(0,10);
					try {
					phone = doc.getElementsByTagName("Phone").item(0).getTextContent();
					} catch (Exception e3) {
						phone = "";
					}
					email = doc.getElementsByTagName("BuyerEmail").item(0).getTextContent();
					
					
					ListOrderItemsRequest request1 = new ListOrderItemsRequest();
					
					request1.setSellerId(sellerId);
			        request1.setMWSAuthToken(mwsAuthToken);
			        request1.setAmazonOrderId(orderID);

			        // Make the call.
			        String itemInfo = invokeListOrderItems(client, request1);
			        DocumentBuilder builder1 = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		        	InputSource src1 = new InputSource();
		        	src1.setCharacterStream(new StringReader(itemInfo));
		        	Document doc1 = builder1.parse(src1);
					sku = doc1.getElementsByTagName("SellerSKU").item(0).getTextContent();
					int underline = sku.indexOf("_");
					if (underline != -1) 
						sku = sku.substring(0, sku.indexOf("_"));
					if (isStdSKU(sku))
						ssku = sku;
					else
						ssku = mapSKU(sku);
					
					asin = doc1.getElementsByTagName("ASIN").item(0).getTextContent();
					product = doc1.getElementsByTagName("Title").item(0).getTextContent();
					
					try {
					FileReader reader = new FileReader("tracking.json");
					JSONObject jsonData = (JSONObject) new JSONParser().parse(reader);
					JSONArray jArray = (JSONArray) jsonData.get(orderID);
					crField.setText((String) jArray.toArray()[0]);
					trackField.setText((String) jArray.toArray()[1]);
					reader.close();
					
					String carrier = crField.getText();
					String trackingNumber = trackField.getText();
					if (carrier.equals("UPS") || carrier.equals("SUREPOST")) {
						uri = new URI("http://wwwapps.ups.com/WebTracking/track?track=yes&trackNums=" + trackingNumber);
					}
					else if (carrier.equals("USPS")) {
						uri = new URI("https://tools.usps.com/go/TrackConfirmAction?tLabels=" + trackingNumber);
					}
					else if (carrier.equals("FEDEX")) {
						uri = new URI("https://www.fedex.com/apps/fedextrack/?tracknumbers=" + trackingNumber);
					}
					else if (carrier.equals("LASERSHIP")) {
						uri = new URI("http://www.lasership.com/track/" + trackingNumber);
					}
					else if (carrier.equals("ONTRAC")) {
						uri = new URI("http://www.ontrac.com/trackingres.asp?tracking_number=" + trackingNumber);
					}
					else if (carrier.equals("DHL")) {
						uri = new URI("http://www.dhl.com/en/express/tracking.html?AWB=" + trackingNumber + "5&brand=DHL");
					}
					else {
						uri = new URI("http://www.myunu.com");
					}
					}
					catch (Exception e4) {
						System.out.println(e4.getMessage());
						logArea.append(LocalDateTime.now().toString() + ": No Tracking Information!\n");
						uri = new URI("http://www.myunu.com");
					}
					// fill value to the appropriate field 
					orderField.setText(orderID);
					skuField.setText(sku);
					asinField.setText(asin);;
					nameField.setText(fullName);
					add1Field.setText(address1);;
					add2Field.setText(address2);
					cityField.setText(city);
					stateField.setText(state);
					zip1Field.setText(zipcode1);
					zip2Field.setText(zipcode2);
					productField.setText(product);;
					
					dateField.setText(purchaseDate);
					phoneField.setText(phone);
					ffField.setText(fulChannel);
					channelField.setText(channel);
					emailField.setText(email);
					sskuField.setText(ssku);
					
					// deal with the refund information
					try {
						FileReader reader = new FileReader("refund.json");
						JSONObject jsonData = (JSONObject) new JSONParser().parse(reader);
						JSONObject thisRefund = (JSONObject) jsonData.get(orderID);
						oriPrice = 0;
						if (thisRefund.get("Ori_Principal") != null) 
							oriPrice = (double) thisRefund.get("Ori_Principal");
						rfPrice = (double) thisRefund.get("Principal");
						shipping = 0;
						if (thisRefund.get("Ori_Shipping") != null) {
							shipping = (double) thisRefund.get("Ori_Shipping");
						}
						rfShipping = 0;
						if (thisRefund.get("Shipping") != null) {
							rfShipping = (double) thisRefund.get("Shipping");
						}
						else if (thisRefund.get("Ori_Shipping2") != null) {
							rfShipping = (double) thisRefund.get("Ori_Shipping2");
						}
						promotion = 0;
						if (thisRefund.get("Ori_Principal2") != null)
							promotion = (double) thisRefund.get("Ori_Principal2");
						rfPromotion = 0;
						if (thisRefund.get("Principal2") != null)
							rfPromotion = (double) thisRefund.get("Principal2");
						tax = 0;
						if (thisRefund.get("Ori_Tax") != null)
								tax = (double) thisRefund.get("Ori_Tax");
						rfTax = 0;
						if (thisRefund.get("Tax") != null)
							rfTax = (double) thisRefund.get("Tax");
						rfpField.setValue(rfPrice);
						rfsField.setValue(rfShipping);
						ppriceField.setValue(oriPrice);
						shippingField.setValue(shipping);
						refund = oriPrice + rfPrice + shipping + rfShipping + promotion +rfPromotion + tax + rfTax;
						refundField.setValue(refund);
						// set the font color based on refund total
						if (refund <= 0) {
							refundField.setForeground(Color.GREEN);
							if (refund < 0)
								logArea.append(LocalDateTime.now().toString() + ": Please see refund detail on website!\n");
						}
						else
							refundField.setForeground(Color.RED);
						
						reader.close();
						}
						catch (Exception e4) {
							System.out.println(e4.getMessage());
							logArea.append(LocalDateTime.now().toString() + ": No Refund Information!\n");
						}

					
					logArea.append(LocalDateTime.now().toString() + ": Process Done!\n");
		        	
		        		
		        		
				}
				catch (Exception e1) {
					System.out.println(e1.getMessage());
					logArea.append(LocalDateTime.now().toString() + ": Invalid Order ID\n");
				}
					
			}
		});
		
		btnProcess.setBounds(15, 574, 89, 23);
		contentPane.add(btnProcess);
		
		// create clear button and its action.
		JButton btnClear = new JButton("Clear");
		btnClear.setAction(action_1);
		btnClear.setBounds(294, 574, 89, 23);
		contentPane.add(btnClear);
		
		// create exit button and its action.
		JButton btnExit = new JButton("Exit");
		btnExit.setAction(action_2);
		btnExit.setBounds(434, 574, 89, 23);
		contentPane.add(btnExit);
		
		JLabel lblInputWindow = new JLabel("Please Input Amazon Order ID:");
		lblInputWindow.setBounds(10, 69, 180, 29);
		contentPane.add(lblInputWindow);
		
		JLabel lblOrderProductInfo = new JLabel("Order/ Product Info");
		lblOrderProductInfo.setBounds(15, 151, 108, 14);
		contentPane.add(lblOrderProductInfo);
		
		JLabel lblOrder = new JLabel("Order #");
		lblOrder.setBounds(15, 176, 46, 14);
		contentPane.add(lblOrder);
		
		JLabel lblSku = new JLabel("Amazon SKU");
		lblSku.setBounds(15, 201, 74, 14);
		contentPane.add(lblSku);
		
		JLabel lblAsin = new JLabel("ASIN");
		lblAsin.setBounds(15, 228, 46, 14);
		contentPane.add(lblAsin);
		
		JLabel lblCustomerInfo = new JLabel("Customer Info");
		lblCustomerInfo.setBounds(357, 154, 108, 14);
		contentPane.add(lblCustomerInfo);
		
		JLabel lblFullName = new JLabel("Full Name");
		lblFullName.setBounds(362, 179, 74, 14);
		contentPane.add(lblFullName);
		
		JLabel lblAddress = new JLabel("Address1");
		lblAddress.setBounds(362, 204, 74, 14);
		contentPane.add(lblAddress);
		
		JLabel lblAddress_1 = new JLabel("Address2");
		lblAddress_1.setBounds(362, 229, 74, 14);
		contentPane.add(lblAddress_1);
		
		JLabel lblCity = new JLabel("City");
		lblCity.setBounds(362, 254, 74, 14);
		contentPane.add(lblCity);
		
		JLabel lblState = new JLabel("State");
		lblState.setBounds(362, 279, 74, 14);
		contentPane.add(lblState);
		
		JLabel lblZip = new JLabel("Zip1");
		lblZip.setBounds(362, 304, 74, 14);
		contentPane.add(lblZip);
		
		JLabel lblZip_1 = new JLabel("Zip2");
		lblZip_1.setBounds(362, 329, 74, 14);
		contentPane.add(lblZip_1);
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setBounds(362, 354, 74, 14);
		contentPane.add(lblPhone);
		
		// field action: double click and select all and copied to clipboard
		orderField = new JTextField();
		orderField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					orderField.selectAll();
					String sltext = orderField.getSelectedText();
					StringSelection data = new StringSelection(sltext);
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents(data, data);
					logArea.append(LocalDateTime.now().toString() + ": Order number has been copied to clipboard.\n");
				}
			}
		});
		orderField.setBounds(123, 176, 202, 20);
		contentPane.add(orderField);
		orderField.setColumns(10);
		
		skuField = new JTextField();
		skuField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					skuField.selectAll();
					String sltext = skuField.getSelectedText();
					StringSelection data = new StringSelection(sltext);
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents(data, data);
					logArea.append(LocalDateTime.now().toString() + ": SKU number has been copied to clipboard.\n");
				}
			}
		});
		skuField.setBounds(123, 201, 202, 20);
		contentPane.add(skuField);
		skuField.setColumns(10);
		
		asinField = new JTextField();
		asinField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					asinField.selectAll();
					String sltext = asinField.getSelectedText();
					StringSelection data = new StringSelection(sltext);
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents(data, data);
					logArea.append(LocalDateTime.now().toString() + ": ASIN number has been copied to clipboard.\n");
				}
			}
		});
		asinField.setBounds(123, 228, 202, 20);
		contentPane.add(asinField);
		asinField.setColumns(10);
		
		nameField = new JTextField();
		nameField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					nameField.selectAll();
					String sltext = nameField.getSelectedText();
					StringSelection data = new StringSelection(sltext);
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents(data, data);
					logArea.append(LocalDateTime.now().toString() + ": Customer name has been copied to clipboard.\n");
				}
			}
		});
		nameField.setBounds(462, 173, 270, 20);
		contentPane.add(nameField);
		nameField.setColumns(10);
		
		add1Field = new JTextField();
		add1Field.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					add1Field.selectAll();
					String sltext = add1Field.getSelectedText();
					StringSelection data = new StringSelection(sltext);
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents(data, data);
					logArea.append(LocalDateTime.now().toString() + ": Address Line 1 has been copied to clipboard.\n");
				}
			}
		});
		add1Field.setBounds(462, 198, 270, 20);
		contentPane.add(add1Field);
		add1Field.setColumns(10);
		
		add2Field = new JTextField();
		add2Field.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					add2Field.selectAll();
					String sltext = add2Field.getSelectedText();
					StringSelection data = new StringSelection(sltext);
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents(data, data);
					logArea.append(LocalDateTime.now().toString() + ": Address Linee 2 has been copied to clipboard.\n");
				}
			}
		});
		add2Field.setBounds(462, 223, 270, 20);
		contentPane.add(add2Field);
		add2Field.setColumns(10);
		
		cityField = new JTextField();
		cityField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					cityField.selectAll();
					String sltext = cityField.getSelectedText();
					StringSelection data = new StringSelection(sltext);
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents(data, data);
					logArea.append(LocalDateTime.now().toString() + ": City has been copied to clipboard.\n");
				}
			}
		});
		cityField.setBounds(462, 248, 270, 20);
		contentPane.add(cityField);
		cityField.setColumns(10);
		
		stateField = new JTextField();
		stateField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					stateField.selectAll();
					String sltext = stateField.getSelectedText();
					StringSelection data = new StringSelection(sltext);
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents(data, data);
					logArea.append(LocalDateTime.now().toString() + ": State has been copied to clipboard.\n");
				}
			}
		});
		stateField.setBounds(462, 273, 270, 20);
		contentPane.add(stateField);
		stateField.setColumns(10);
		
		zip1Field = new JTextField();
		zip1Field.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					zip1Field.selectAll();
					String sltext = zip1Field.getSelectedText();
					StringSelection data = new StringSelection(sltext);
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents(data, data);
					logArea.append(LocalDateTime.now().toString() + ": Zipcode(main) has been copied to clipboard.\n");
				}
			}
		});
		zip1Field.setBounds(462, 298, 270, 20);
		contentPane.add(zip1Field);
		zip1Field.setColumns(10);
		
		zip2Field = new JTextField();
		zip2Field.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					zip2Field.selectAll();
					String sltext = zip2Field.getSelectedText();
					StringSelection data = new StringSelection(sltext);
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents(data, data);
					logArea.append(LocalDateTime.now().toString() + ": Sub Zipcode has been copied to clipboard.\n");
				}
			}
		});
		zip2Field.setBounds(462, 323, 270, 20);
		contentPane.add(zip2Field);
		zip2Field.setColumns(10);
		
		phoneField = new JTextField();
		phoneField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					phoneField.selectAll();
					String sltext = phoneField.getSelectedText();
					StringSelection data = new StringSelection(sltext);
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents(data, data);
					logArea.append(LocalDateTime.now().toString() + ": phone number has been copied to clipboard.\n");
				}
			}
		});
		phoneField.setBounds(462, 348, 270, 20);
		contentPane.add(phoneField);
		phoneField.setColumns(10);
		
		JLabel lblUtilitiesDataTools = new JLabel("Utilities Data Tools");
		lblUtilitiesDataTools.setBounds(789, 47, 145, 14);
		contentPane.add(lblUtilitiesDataTools);
		
		JLabel lblReplacementStdData = new JLabel("Replacement Std Data Row");
		lblReplacementStdData.setBounds(789, 69, 172, 14);
		contentPane.add(lblReplacementStdData);
		
		replaceStdField = new JTextField();
		replaceStdField.setEditable(false);
		replaceStdField.setBounds(789, 91, 145, 20);
		contentPane.add(replaceStdField);
		replaceStdField.setColumns(10);
		replaceStdField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					replaceStdField.selectAll();
					String sltext = replaceStdField.getSelectedText();
					StringSelection data = new StringSelection(sltext);
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents(data, data);
					logArea.append(LocalDateTime.now().toString() + ": Raw Date has been copied to clipboard.\n");
				}
			}
		});
		
		JLabel lblReplacementFastData = new JLabel("Replacement Fast Data Row");
		lblReplacementFastData.setBounds(789, 143, 172, 14);
		contentPane.add(lblReplacementFastData);
		
		textField_12 = new JTextField();
		textField_12.setEditable(false);
		textField_12.setBounds(789, 168, 145, 20);
		contentPane.add(textField_12);
		textField_12.setColumns(10);
		
		JLabel lblReturnLabelData = new JLabel("Return Label Data Row");
		lblReturnLabelData.setBounds(789, 220, 172, 14);
		contentPane.add(lblReturnLabelData);
		
		textField_13 = new JTextField();
		textField_13.setEditable(false);
		textField_13.setBounds(789, 245, 145, 20);
		contentPane.add(textField_13);
		textField_13.setColumns(10);
		
		JLabel lblProduct = new JLabel("Product");
		lblProduct.setBounds(15, 259, 46, 14);
		contentPane.add(lblProduct);
		
		productField = new JTextField();
		productField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					productField.selectAll();
					String sltext = productField.getSelectedText();
					StringSelection data = new StringSelection(sltext);
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents(data, data);
					logArea.append(LocalDateTime.now().toString() + ": Product information has been copied to clipboard.\n");
				}
			}
		});
		productField.setColumns(10);
		productField.setBounds(123, 259, 202, 20);
		contentPane.add(productField);
		
		JLabel lblRefundTotal = new JLabel("Refund Total");
		lblRefundTotal.setBounds(362, 416, 74, 14);
		contentPane.add(lblRefundTotal);
		
		refundField = new JFormattedTextField();
		refundField.setColumns(10);
		refundField.setBounds(462, 410, 270, 20);
		contentPane.add(refundField);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(15, 293, 46, 14);
		contentPane.add(lblDate);
		
		dateField = new JTextField();
		dateField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					dateField.selectAll();
					String sltext = dateField.getSelectedText();
					StringSelection data = new StringSelection(sltext);
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents(data, data);
					logArea.append(LocalDateTime.now().toString() + ": Purchase Date has been copied to clipboard.\n");
				}
			}
		});
		dateField.setColumns(10);
		dateField.setBounds(123, 290, 202, 20);
		contentPane.add(dateField);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 640, 1008, 90);
		contentPane.add(scrollPane);
		
		// log area, show the mouse action info 
		logArea = new JTextArea();
		scrollPane.setViewportView(logArea);
		logArea.setForeground(Color.BLUE);
		logArea.setEditable(false);
		
		JButton btnExport = new JButton("Export");
		btnExport.setBounds(155, 574, 89, 23);
		contentPane.add(btnExport);
		btnExport.setAction(action_3);
		
		JLabel lblZendeskTicket = new JLabel("ZenDesk ticket #");
		lblZendeskTicket.setBounds(789, 302, 145, 14);
		contentPane.add(lblZendeskTicket);
		
		ticketNField = new JTextField();
		ticketNField.setColumns(10);
		ticketNField.setBounds(789, 324, 145, 20);
		contentPane.add(ticketNField);
		
		JButton btnPopulate = new JButton("Populate");
		btnPopulate.setAction(action);
		btnPopulate.setBounds(768, 355, 89, 23);
		contentPane.add(btnPopulate);
		
		JButton btnRestore = new JButton("Restore");
		btnRestore.setAction(action_4);
		btnRestore.setBounds(867, 355, 90, 23);
		contentPane.add(btnRestore);
		
		idInputField = new JTextField();
		idInputField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		idInputField.setBounds(10, 103, 235, 20);
		contentPane.add(idInputField);
		idInputField.setColumns(10);
		
		JLabel lblFullfillment = new JLabel("Fulfillment");
		lblFullfillment.setBounds(15, 327, 74, 14);
		contentPane.add(lblFullfillment);
		
		ffField = new JTextField();
		ffField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					ffField.selectAll();
					String sltext = ffField.getSelectedText();
					StringSelection data = new StringSelection(sltext);
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents(data, data);
					logArea.append(LocalDateTime.now().toString() + ": Fullfilled Channel has been copied to clipboard.\n");
				}
			}
		});
		ffField.setColumns(10);
		ffField.setBounds(123, 324, 202, 20);
		contentPane.add(ffField);
		
		JLabel lblChannel = new JLabel("Channel");
		lblChannel.setBounds(15, 358, 74, 14);
		contentPane.add(lblChannel);
		
		channelField = new JTextField();
		channelField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					channelField.selectAll();
					String sltext = channelField.getSelectedText();
					StringSelection data = new StringSelection(sltext);
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents(data, data);
					logArea.append(LocalDateTime.now().toString() + ": Channel has been copied to clipboard.\n");
				}
			}
		});
		channelField.setColumns(10);
		channelField.setBounds(123, 355, 202, 20);
		contentPane.add(channelField);
		
		JLabel lblStandardSku = new JLabel("Standard SKU");
		lblStandardSku.setBounds(15, 385, 108, 14);
		contentPane.add(lblStandardSku);
		
		sskuField = new JTextField();
		sskuField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					sskuField.selectAll();
					String sltext = sskuField.getSelectedText();
					StringSelection data = new StringSelection(sltext);
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents(data, data);
					logArea.append(LocalDateTime.now().toString() + ": Standard SKU has been copied to clipboard.\n");
				}
			}
		});
		sskuField.setColumns(10);
		sskuField.setBounds(123, 382, 202, 20);
		contentPane.add(sskuField);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(362, 385, 74, 14);
		contentPane.add(lblEmail);
		
		emailField = new JTextField();
		emailField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					emailField.selectAll();
					String sltext = emailField.getSelectedText();
					StringSelection data = new StringSelection(sltext);
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents(data, data);
					logArea.append(LocalDateTime.now().toString() + ": Customer email has been copied to clipboard.\n");
				}
			}
		});
		emailField.setColumns(10);
		emailField.setBounds(462, 379, 270, 20);
		contentPane.add(emailField);
		
		JLabel lblCarrier = new JLabel("Carrier");
		lblCarrier.setBounds(15, 413, 108, 14);
		contentPane.add(lblCarrier);
		
		crField = new JTextField();
		crField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					crField.selectAll();
					String sltext = crField.getSelectedText();
					StringSelection data = new StringSelection(sltext);
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents(data, data);
					logArea.append(LocalDateTime.now().toString() + ": Carrier Information has been copied to clipboard.\n");
				}
			}
		});
		crField.setColumns(10);
		crField.setBounds(123, 410, 74, 20);
		contentPane.add(crField);
		
		JLabel lblTracking = new JLabel("Tracking #");
		lblTracking.setBounds(15, 441, 108, 14);
		contentPane.add(lblTracking);
		
		trackField = new JTextField();
		trackField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					trackField.selectAll();
					String sltext = trackField.getSelectedText();
					StringSelection data = new StringSelection(sltext);
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents(data, data);
					logArea.append(LocalDateTime.now().toString() + ": Tracking number has been copied to clipboard.\n");
				}
			}
		});
		trackField.setColumns(10);
		trackField.setBounds(123, 438, 202, 20);
		contentPane.add(trackField);
		
		JLabel lblRefundPrincple = new JLabel("Refund Principal");
		lblRefundPrincple.setBounds(558, 447, 103, 14);
		contentPane.add(lblRefundPrincple);
		
		rfpField = new JFormattedTextField();
		rfpField.setColumns(10);
		rfpField.setBounds(658, 441, 74, 20);
		contentPane.add(rfpField);
		
		JLabel lblRefundShipping = new JLabel("Refund Shipping");
		lblRefundShipping.setBounds(558, 478, 103, 14);
		contentPane.add(lblRefundShipping);
		
		rfsField = new JFormattedTextField();
		rfsField.setColumns(10);
		rfsField.setBounds(658, 472, 74, 20);
		contentPane.add(rfsField);
		
		JLabel lblPrincipalPrice = new JLabel("Principal Price");
		lblPrincipalPrice.setBounds(362, 447, 103, 14);
		contentPane.add(lblPrincipalPrice);
		
		ppriceField = new JFormattedTextField();
		ppriceField.setColumns(10);
		ppriceField.setBounds(462, 441, 74, 20);
		contentPane.add(ppriceField);
		
		JLabel lblShipping = new JLabel("Shipping");
		lblShipping.setBounds(362, 478, 103, 14);
		contentPane.add(lblShipping);
		
		shippingField = new JFormattedTextField();
		shippingField.setColumns(10);
		shippingField.setBounds(462, 472, 74, 20);
		contentPane.add(shippingField);
		
		JLabel lblPromotion = new JLabel("Promotion");
		lblPromotion.setBounds(362, 509, 103, 14);
		contentPane.add(lblPromotion);
		
		JFormattedTextField promoTextField = new JFormattedTextField();
		promoTextField.setColumns(10);
		promoTextField.setBounds(462, 503, 74, 20);
		contentPane.add(promoTextField);
		
		JLabel lblRefundPromotion = new JLabel("Refund Promotion");
		lblRefundPromotion.setBounds(558, 509, 103, 14);
		contentPane.add(lblRefundPromotion);
		
		JFormattedTextField rPromoTextField = new JFormattedTextField();
		rPromoTextField.setColumns(10);
		rPromoTextField.setBounds(658, 503, 74, 20);
		contentPane.add(rPromoTextField);
		
		
		// uri = new URI("http://www.myunu.com");
		
		class OpenUrlAction implements ActionListener {
			@Override 
			public void actionPerformed(ActionEvent e) {
				open(uri);
			}
		}
		
		JButton btnTrackingInfo = new JButton("Tracking Info");
		btnTrackingInfo.setBounds(207, 407, 118, 23);
		btnTrackingInfo.addActionListener(new OpenUrlAction());
		contentPane.add(btnTrackingInfo);
		
		
		
	}
	
	private static void open(URI uri) {
		if (Desktop.isDesktopSupported()) {
			try {
				Desktop.getDesktop().browse(uri);
			} catch (IOException e) {
				System.out.println(e);
			}
		}
		else {}
	}
	
	
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Populate");
			putValue(SHORT_DESCRIPTION, "Populate data to zendesk");
		}
		public void actionPerformed(ActionEvent e) {
			int selectedOption = JOptionPane.showConfirmDialog(null, 
					"Please make sure the ticket number and information are correct!",
					"Warning",
					JOptionPane.YES_NO_OPTION);
			if (selectedOption == JOptionPane.YES_OPTION) {
				
			String str = ticketNField.getText();
			int ticketID = Integer.parseInt(str);
			try {
				String support = brand +".zendesk.com";
				Zendesk zd = new Zendesk.Builder("https://" + support)
						.setUsername(loginID).setPassword(passwd)
				        // .setToken("g0YVCKIJdndLlGFZLouccN38rrgefbAiIL5SrACZ") // or .setPassword("...")
				        .build();
				ticket_bak = new Ticket();
				ticket_bak = zd.getTicket((long) ticketID);
				ticket = new Ticket();
				ticket = zd.getTicket((long) ticketID);
				
				String filePath = loginID + "config.json";
				FileReader reader = new FileReader(filePath);
				fieldData = (JSONObject) new JSONParser().parse(reader);
				
				ArrayList<CustomFieldValue> custom_fields = new ArrayList<CustomFieldValue>();
				addValidField(custom_fields,"fullName", fullName);
				addValidField(custom_fields,"address1", address1);
				addValidField(custom_fields,"address2", address2);
				addValidField(custom_fields,"city", city);
				addValidField(custom_fields,"state", state);
				addValidField(custom_fields,"zipcode1", zipcode1);
				addValidField(custom_fields,"zipcode2", zipcode2);
				addValidField(custom_fields,"country", "US");
				addValidField(custom_fields,"phone", phone);
				addValidField(custom_fields,"purchase_date", purchaseDate);
				addValidField(custom_fields,"orderID", orderID);
				addValidField(custom_fields,"product", product);
			
				ticket.setCustomFields(custom_fields);
				
				zd.updateTicket(ticket);
				zd.close();
				String mm = ": ticket " + Integer.toString(ticketID) + " has been updated.\n";
				logArea.append(LocalDateTime.now().toString() + mm);
			}
			catch (Exception err)
			{
				System.out.println(err);
				err.getMessage();
				logArea.append(LocalDateTime.now().toString() + ": Information can't be update to the ticket, please double check!");
			}
			}
		}
	}
	// add valid field
	public static List<CustomFieldValue> addValidField (List<CustomFieldValue> cfv, String attr, String value) {
		long fieldID = (long) fieldData.get(attr);
		if (fieldID != 0) {
			CustomFieldValue c1 = new CustomFieldValue(fieldID, value);
			cfv.add(c1);
		}
		
		return cfv;
	}
	// clear action
	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "Clear");
			putValue(SHORT_DESCRIPTION, "Clear all text");
		}
		public void actionPerformed(ActionEvent e) {
			idInputField.setText("");
			clearTextFields();
		}
	}
	
	public void clearTextFields() {
		orderField.setText("");
		skuField.setText("");
		asinField.setText("");;
		nameField.setText("");
		add1Field.setText("");;
		add2Field.setText("");
		cityField.setText("");
		stateField.setText("");
		zip1Field.setText("");
		zip2Field.setText("");
		productField.setText("");;
		refundField.setText("");
		dateField.setText("");
		phoneField.setText("");
		ffField.setText("");
		channelField.setText("");
		emailField.setText("");
		sskuField.setText("");
		crField.setText("");
		trackField.setText("");
		replaceStdField.setText("");
		
	}
	//exit action
	private class SwingAction_2 extends AbstractAction {
		public SwingAction_2() {
			putValue(NAME, "Exit");
			putValue(SHORT_DESCRIPTION, "Exit the Application");
		}
		public void actionPerformed(ActionEvent e) {
			int selectedOption = JOptionPane.showConfirmDialog(null, 
					"Are you sure to exit?",
					"Warning",
					JOptionPane.YES_NO_OPTION);
			if (selectedOption == JOptionPane.YES_OPTION) {
				try {
					String logfile = LocalDateTime.now().toString().replaceAll(":", "") +".log";
					BufferedWriter out = new BufferedWriter(new FileWriter(logfile));
					out.write(logArea.getText());
					out.flush();
					out.close();
					System.exit(0);
				}
				catch (Exception e1) {
					e1.getMessage();
				}
				
			}
		}
	}
	private class SwingAction_3 extends AbstractAction {
		public SwingAction_3() {
			putValue(NAME, "Export");
			putValue(SHORT_DESCRIPTION, "Export data to row");
		}
		public void actionPerformed(ActionEvent e) {
			String outRow = "";
			if (!ssku.equals(ssku)) 
			outRow = "\t" + orderID + "\t\t\t\t" + ssku + "\t\t\t" + fullName +"\t\t" + address1 + "\t" + address2 + "\t" + city + "\t" + state +"\t" + zipcode1 + "\tUS\t\t" + phone;
			else
				outRow = "\t" + orderID + "\t\t\t\t" + sku + "\t\t\t" + fullName +"\t\t" + address1 + "\t" + address2 + "\t" + city + "\t" + state +"\t" + zipcode1 + "\tUS\t\t" + phone;
			replaceStdField.setText(outRow);
		}
	}
	private class SwingAction_4 extends AbstractAction {
		public SwingAction_4() {
			putValue(NAME, "Restore");
			putValue(SHORT_DESCRIPTION, "Restore ticket to previous status");
		}
		public void actionPerformed(ActionEvent e) {
			try {
				
				String support = brand +".zendesk.com";
				Zendesk zd = new Zendesk.Builder("https://" + support)
						.setUsername(loginID).setPassword(passwd).build();
				/*
				if (ticket_bak.equals(null))
					System.out.println("Sorry, no previous data.");
				else
					zd.updateTicket(ticket_bak);
				*/
				//System.out.println(ticket_bak.getCustomFields().get(0).getValue());
				String str = ticketNField.getText();
				int ticketID = Integer.parseInt(str);
				String mm = "";
				if (ticket_bak.getId() == ticketID) {
					zd.updateTicket(ticket_bak);
				    mm = ": ticket " + ticket_bak.getId() + " has been restored.\n";
				}
				else {
					mm = "You cannot restore this ticket.\n";
				}
				logArea.append(LocalDateTime.now().toString() + mm);
				zd.close();
			}
			catch (Exception err) {
				System.out.println(err);
			}
		}
	}
	public void setLoginID (String str) {
		loginID = str;
	}
	public void setPassword (String pass) {
		passwd = pass;
	}
	public void setBrand (String bd) {
		brand = bd;
	}
	
	public static String invokeGetOrder(
            MarketplaceWebServiceOrders client, 
            GetOrderRequest request) {
        try {
            // Call the service.
            GetOrderResponse response = client.getOrder(request);
            ResponseHeaderMetadata rhmd = response.getResponseHeaderMetadata();
            // We recommend logging every the request id and timestamp of every call.
            System.out.println("Response:");
            System.out.println("RequestId: "+rhmd.getRequestId());
            System.out.println("Timestamp: "+rhmd.getTimestamp());
            String responseXml = response.toXML();
            System.out.println(responseXml);
            return responseXml;
        } catch (MarketplaceWebServiceOrdersException ex) {
            // Exception properties are important for diagnostics.
            System.out.println("Service Exception:");
            ResponseHeaderMetadata rhmd = ex.getResponseHeaderMetadata();
            if(rhmd != null) {
                System.out.println("RequestId: "+rhmd.getRequestId());
                System.out.println("Timestamp: "+rhmd.getTimestamp());
            }
            System.out.println("Message: "+ex.getMessage());
            System.out.println("StatusCode: "+ex.getStatusCode());
            System.out.println("ErrorCode: "+ex.getErrorCode());
            System.out.println("ErrorType: "+ex.getErrorType());
            throw ex;
        }
    }
	
	public static String invokeListOrderItems(
            MarketplaceWebServiceOrders client, 
            ListOrderItemsRequest request) {
        try {
            // Call the service.
            ListOrderItemsResponse response = client.listOrderItems(request);
            ResponseHeaderMetadata rhmd = response.getResponseHeaderMetadata();
            // We recommend logging every the request id and timestamp of every call.
            System.out.println("Response:");
            System.out.println("RequestId: "+rhmd.getRequestId());
            System.out.println("Timestamp: "+rhmd.getTimestamp());
            String responseXml = response.toXML();
            System.out.println(responseXml);
            return responseXml;
        } catch (MarketplaceWebServiceOrdersException ex) {
            // Exception properties are important for diagnostics.
            System.out.println("Service Exception:");
            ResponseHeaderMetadata rhmd = ex.getResponseHeaderMetadata();
            if(rhmd != null) {
                System.out.println("RequestId: "+rhmd.getRequestId());
                System.out.println("Timestamp: "+rhmd.getTimestamp());
            }
            System.out.println("Message: "+ex.getMessage());
            System.out.println("StatusCode: "+ex.getStatusCode());
            System.out.println("ErrorCode: "+ex.getErrorCode());
            System.out.println("ErrorType: "+ex.getErrorType());
            throw ex;
        }
    }
	
	public static String mapSKU (String amzSku) {
		String stdSku = "";
		try {
		String file = "mappingSKU";
		if (!checkMD5(file + ".csv")) {
			loadMappingSku(file + ".csv");
		}
		FileReader reader = new FileReader(file+".json");
		JSONObject jsonData = (JSONObject) new JSONParser().parse(reader);
		if (jsonData.containsKey(amzSku))
			stdSku = (String) jsonData.get(amzSku);
		else if (jsonData.containsKey(amzSku+"-F"))
			stdSku = (String) jsonData.get(amzSku+"-F");
		else if (jsonData.containsKey(amzSku+"-FBA"))
			stdSku = (String) jsonData.get(amzSku+"-FBA");
		
		} catch (Exception e1) {
			logArea.append(LocalDateTime.now().toString() + ":" + e1.getMessage() + "\n");
		}
		
		return stdSku;
	}
	
	public static boolean isStdSKU (String amzSku) {
		boolean flag = false;
		try {
			FileReader reader = new FileReader("mappingSKU.json");
			JSONObject jsonData = (JSONObject) new JSONParser().parse(reader);
			if (jsonData.containsValue(amzSku))
				flag = true;
		}
		catch (Exception e) {
			logArea.append(LocalDateTime.now().toString() + ":" + e.getMessage() + "\n");
		}
		return flag;
	}
	
	public static boolean checkMD5(String file) {
		boolean flag = true;
		try {
		File theFile = new File(file);
		FileReader reader = new FileReader("md5");
		BufferedReader in = new BufferedReader(reader);
		String md5 = in.readLine();
		//Use MD5 algorithm
		MessageDigest md5Digest = MessageDigest.getInstance("MD5");
		 
		//Get the checksum
		String checksum = getMD5(md5Digest, theFile);
		if (!checksum.equals(md5)) 
			flag = false;
		in.close();
		reader.close();
		}
		catch (Exception e) {
			logArea.append(LocalDateTime.now().toString() + ":" + e.getMessage() + "\n");
		}
		return flag;
	}
	
	public static String getMD5(MessageDigest digest, File file) throws IOException {
		FileInputStream fis = new FileInputStream(file);
	     
	    //Create byte array to read data in chunks
	    byte[] byteArray = new byte[1024];
	    int bytesCount = 0; 
	      
	    //Read file data and update in message digest
	    while ((bytesCount = fis.read(byteArray)) != -1) {
	        digest.update(byteArray, 0, bytesCount);
	    };
	     
	    //close the stream; We don't need it now.
	    fis.close();
	     
	    //Get the hash's bytes
	    byte[] bytes = digest.digest();
	     
	    //This bytes[] has bytes in decimal format;
	    //Convert it to hexadecimal format
	    StringBuilder sb = new StringBuilder();
	    for(int i=0; i< bytes.length ;i++)
	    {
	        sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
	    }
	    String filePath = "md5";
		BufferedWriter out = new BufferedWriter(new FileWriter(filePath));
		out.write(sb.toString());
		out.flush();
		out.close();
	    //return complete hash
	    return sb.toString();
	}
	
	public static void loadMappingSku(String file) {
		try {
			FileReader reader = new FileReader(file);
			BufferedReader in = new BufferedReader(reader);
			String line = "";
			String filePath = file.substring(0,file.indexOf(".")) + ".json";
			BufferedWriter out = new BufferedWriter(new FileWriter(filePath));
			JSONObject obj = new JSONObject();
			
			while((line = in.readLine()) != null) {
				String value = line.substring(0, line.indexOf(","));
				String key = line.substring(line.indexOf(",")+1);
				obj.putIfAbsent(key, value);
			}
			in.close();
			out.write(obj.toJSONString());
			out.flush();
			out.close();
			}
			catch (Exception e) {
				logArea.append(LocalDateTime.now().toString() + ":" + e.getMessage() + "\n");
			}
	}
	
	public static boolean isFileExsit(String file) {
		File f = new File(file);
		if(f.exists() && !f.isDirectory()) { 
		    return true;
		}
		else {
			return false;
		}
	}
	
	public void setSellerID (String sellid) {
		sellerId = rot13(sellid);
		System.out.println(sellerId);
	}
	
	public String rot13(String str) {
        String result = "";
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if       (c >= 'a' && c <= 'm') c += 13;
            else if  (c >= 'A' && c <= 'M') c += 13;
            else if  (c >= 'n' && c <= 'z') c -= 13;
            else if  (c >= 'N' && c <= 'Z') c -= 13;
            System.out.print(c);
            result += c;
        }
        
        return result;
    }
}
