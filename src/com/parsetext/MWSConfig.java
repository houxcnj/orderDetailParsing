/*******************************************************************************
 * Copyright 2009-2015 Amazon Services. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 *
 * You may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at: http://aws.amazon.com/apache2.0
 * This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR 
 * CONDITIONS OF ANY KIND, either express or implied. See the License for the 
 * specific language governing permissions and limitations under the License.
 *******************************************************************************
 * Marketplace Web Service Orders
 * API Version: 2013-09-01
 * Library Version: 2015-09-24
 * Generated: Fri Sep 25 20:06:20 GMT 2015
 */
package com.parsetext;

import java.io.BufferedReader;
import java.io.FileReader;

import com.amazonservices.mws.orders._2013_09_01.MWSEndpoint;
import com.amazonservices.mws.orders._2013_09_01.MarketplaceWebServiceOrdersAsyncClient;
import com.amazonservices.mws.orders._2013_09_01.MarketplaceWebServiceOrdersClient;
import com.amazonservices.mws.orders._2013_09_01.MarketplaceWebServiceOrdersConfig;

/**
 * Configuration for MarketplaceWebServiceOrders.
 */
public class MWSConfig {
	
    /** Developer AWS access key. */
    private static String accessKey = "";

    /** Developer AWS secret key. */
    private static String secretKey = "";

    /** The client application name. */
    private static final String appName = "";

    /** The client application version. */
    private static final String appVersion = "";

    /**
     * The endpoint for region service and version.
     * ex: serviceURL = MWSEndpoint.NA_PROD.toString();
     */
    private static final String serviceURL = MWSEndpoint.NA_PROD.toString();

    /** The client, lazy initialized. Async client is also a sync client. */
    private static MarketplaceWebServiceOrdersAsyncClient client = null;

    /**
     * Get a client connection ready to use.
     *
     * @return A ready to use client connection.
     */
    public static MarketplaceWebServiceOrdersClient getClient() {
        return getAsyncClient();
    }

    /**
     * Get an async client connection ready to use.
     *
     * @return A ready to use client connection.
     */
    public static synchronized MarketplaceWebServiceOrdersAsyncClient getAsyncClient() {
        if (client==null) {
            MarketplaceWebServiceOrdersConfig config = new MarketplaceWebServiceOrdersConfig();
            config.setServiceURL(serviceURL);
            // Set other client connection configurations here.
            try {
            	FileReader reader = new FileReader("myKey.txt");
            	BufferedReader in = new BufferedReader(reader);
            	String line = in.readLine();
            	// while ((line = in.readLine()).indexOf("AWS") == -1) ;
            	line = in.readLine();
            	String enaccessKey = line;// line.substring(line.indexOf("\t")+1);
            	line = in.readLine();
            	String ensecretKey = line; // line.substring(line.indexOf("\t")+1);
            	
            	
            	accessKey = rot13(enaccessKey);
            	secretKey = rot13(ensecretKey);
            	
            	System.out.println(accessKey);
            	System.out.println(secretKey);
            	in.close();
            	
            	
            	} catch (Exception e) {
            		e.getMessage();
            	}

            client = new MarketplaceWebServiceOrdersAsyncClient(accessKey, secretKey, 
                    appName, appVersion, config, null);
        }
        return client;
    }
    
    public static String rot13(String str) {
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
