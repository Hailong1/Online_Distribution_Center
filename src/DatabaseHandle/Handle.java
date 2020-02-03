/**
 * 
 */
package DatabaseHandle;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



/**
 * @author Hailong 
 *
 */
public class Handle {
	
	    PrintTo print=new PrintTo();
		/**
		 * 
		 * @return
		 * @throws SQLException 
		 * @throws IOException 
		 * @throws FileNotFoundException 
		 * @throws ClassNotFoundException 
		 */
		
		public Connection conDatabase() throws ClassNotFoundException, SQLException{
			
			    Class.forName("com.mysql.jdbc.Driver") ;   
			    String []infor; 
			    infor=getInfor("D:\\Users\\ThinkPad\\workspace\\DataBase\\DataInfor.txt"); // enter corresponding path where the database information locate
			    Connection dbConnection=DriverManager.getConnection(infor[0],infor[1],infor[2]);
					   if(!dbConnection.isClosed()) {         
						   print.pl("Succeeded connecting to the Database!"); 
					   }	
			 return dbConnection;
				
		}
		
		/**
		 *  get the information of corresponding database from the "DataInfor.txt"
		 * @param path
		 * @return three string value. 
		 * the first one store the url
		 * the second one store the username
		 * the third one store the password.     
		 */	
		  public String[] getInfor(String path)
		{
			    ProcessFromTxt pf=new ProcessFromTxt();
			    ArrayList<String> list=new ArrayList<String>();
			    String url="jdbc:mysql://localhost:3306/";
			    list=pf.readTxt(path); 
			    String []infor=new String[3];
			    for(int i=0; i<list.size();i++)
			    {
			    	String []temp=list.get(i).split(":");
			    	if (i==0)
			    		infor[0]=url+temp[1].trim(); // get the url information, temp[1] store the database information. 
			    	if (i<=2&&i>0)
			    	    infor[i]=temp[1].trim();
			    }
			    return infor;
		}
		
		
		/**
		 * update the information.
		 * @param table
		 * @param attribute
		 * @param value
		 * @param user
		 */
		public void updateInfor(String table,String attribute,String value,String conattribute,String user)
		{
			   String update=null;
			   update="update "+table+" set "+ attribute+"='"+value+"' where "+conattribute+"= '"+user+"';";
			   print.pl("update sentence: "+update);
			   buildTable(update);
    	}
		
    	/**
		 * 
		 * @param build
		 */
		public void buildTable(String build)
		{
		try {
				PreparedStatement sql;
				Connection dbConnection=conDatabase();
				sql=dbConnection.prepareStatement(build);
				sql.executeUpdate();											
			}  catch (Exception e) {
				// TODO Auto-generated catch block
				print.pl("Unable to connect properly to database.");
				System.err.println(e.getMessage());
			}
			
		}
		
	   /**
	    * select one value for string 
	    * @param select
	    * @param column
	    * @return
	    */
		public String getOneValue(String select,int column)
		{
		  String result="false";
		  print.pl("column:"+column);
		  try{
			  PreparedStatement sql;
			  Connection dbConnection=conDatabase();
			  ResultSet rs=null;
			  sql=dbConnection.prepareStatement(select);
			  rs=sql.executeQuery();
			  while(rs.next())
			  {
				  result=rs.getString(column);
			  }
				
			}
			catch(Exception e)
			{
				// TODO Auto-generated catch block
				print.pl("Unable to connect properly to database.");
				System.err.println(e.getMessage());
			}
		
		   return result;
		}
		
		/**
		 * select whole value for a column
		 * @param select
		 * @param column
		 * @return
		 */
		public ArrayList<String>  getOneListValue(String select,int column)
		{
		  ArrayList<String>list=new ArrayList<String>();
//		  print.pl(column);
		  try{
			  PreparedStatement sql;
			  Connection dbConnection=conDatabase();
			  ResultSet rs=null;
			  sql=dbConnection.prepareStatement(select);
			  rs=sql.executeQuery();
			  while(rs.next())
			  {
				  list.add(rs.getString(column));
			  }
				
			}
			catch(Exception e)
			{
				// TODO Auto-generated catch block
				print.pl("Unable to connect properly to database.");
				System.err.println(e.getMessage());
			}
		
		   return list;
		}
	   /**
	    * select whole one information.  
	    * @param select
	    * @param column
	    * @return
	    */
		
		public ArrayList<String>  getWholeOneListValue(String select,int wholecolumn)
		{
		  ArrayList<String>list=new ArrayList<String>();
		  int countcolumn=wholecolumn-wholecolumn+1;
//		  print.pl(countcolumn);
		  try{
			  PreparedStatement sql;
			  Connection dbConnection=conDatabase();
			  ResultSet rs=null;
			  sql=dbConnection.prepareStatement(select);
			  rs=sql.executeQuery();
			  while(rs.next())
			  {
				  while(countcolumn<=wholecolumn)
				  {list.add(rs.getString(countcolumn));
				   countcolumn++;
				  }
			  }
//			  print.pl("after:"+countcolumn);
				
			}
			catch(Exception e)
			{
				// TODO Auto-generated catch block
				print.pl("Unable to connect properly to database.");
				System.err.println(e.getMessage());
			}
		
		   return list;
		}
		
		
		
		
		/**
		 * judge the use is exist. 
		 * @param select
		 * @param user
		 * @return
		 */
		public String handleUseSql(String select,String user){
			String result="false";
			try{
				       Connection dbConnection=conDatabase();
				       PreparedStatement sqlselect=dbConnection.prepareStatement(select);
				       ResultSet rs=sqlselect.executeQuery();
				       print.pl("select="+select);
			           while(rs.next()){
				    		   print.pl(rs.getString(2));
				    		   result="true";
				    		   break;
				       }
			   
	/**	             if(flag==false){
		            	  PreparedStatement sqlinsert=dbConnection.prepareStatement(insert);
					       sqlinsert.executeUpdate();
					       br.setLength(0);
					       }	  */ 
				}	
			catch(Exception e){
				print.pl("Unable to connect properly to database.");
				System.err.println(e.getMessage());
			}
			return result;
		}
		
		/**
		 * judge the regionid or categoryid or warehouseid is exist
		 * @param select
		 * @param regionid
		 * @return
		 */
		
		public String handleRegionSql(String select,String regionid){
			String result="false";
			try{
				       Connection dbConnection=conDatabase();
				       PreparedStatement sqlselect=dbConnection.prepareStatement(select);
				       ResultSet rs=sqlselect.executeQuery();
	//			       print.pl("select="+select);
			           while(rs.next()){
	//		        	       print.pl(rs.getString(1));
				    		   result=rs.getString(1);
				    		   break;
				       }
				}	
			catch(Exception e){
				print.pl("Unable to connect properly to database.");
				System.err.println(e.getMessage());
			}
			return result;
		}
		
		
		 /**
		  * judge the login system. 
		  * @param select
		  * @param user
		  * @param pwd
		  * @return
		  */
        public String login(String select, String user,String pwd){
	    	 String result="null";
			 
	 	             try{
	 				   Connection dbConnection=conDatabase();
	 				   PreparedStatement sql=dbConnection.prepareStatement(select);
	 				   String salt = null;
	 			       ResultSet rs=sql.executeQuery();
	 			       System.out.println("select="+select);
				      if(rs.next())
				      {
	                         salt=rs.getString("usersalt");
	      				     String phash = getHash(pwd, salt);
				    	     rs=sql.executeQuery();
				    	     while(rs.next())
				    	     {
				    	      if(phash.equals(rs.getString("passhash")))
				    	      {
	 			    		     result="true";
	 			              }
	 			    	      else 
	 			    	      {
	 			    	    	  result="false";
	 			    	      }
	 			             }
				      }
	 			        else result="false";
	 			 	
	 	             }catch(Exception e){
	 	            	print.pl("Unable to connect properly to database.");
	 	   			System.err.println(e.getMessage());
	 	   		}

	 						 
	    	 return result;
	     }

		
		
		  /**
				 * Returns the hex encoding of a byte array.
				 *
				 * @param bytes
				 *            - byte array to encode
				 * @param length
				 *            - desired length of encoding
				 * @return hex encoded byte array
				 */
				public  String encodeHex(byte[] bytes, int length) {
					BigInteger bigint = new BigInteger(1, bytes);
					String hex = String.format("%0" + length + "X", bigint);

					assert hex.length() == length;
					return hex;
				}

				/**
				 * Calculates the hash of a password and salt using SHA-256.
				 *
				 * @param password
				 *            - password to hash
				 * @param salt
				 *            - salt associated with user
				 * @return hashed password
				 */
				public String getHash(String password, String salt) {
					String salted = salt + password;
					String hashed = salted;

					try {
						MessageDigest md = MessageDigest.getInstance("SHA-256");
						md.update(salted.getBytes());
						hashed = encodeHex(md.digest(), 64);
					} catch (Exception ex) {
						System.out.println("Unable to properly hash password." + ex);
					}

					return hashed;
				}
				
			/**
			 * show the whole page for customer to show all products and update the quantity
			 * choose to back which place.
			 * @param select
			 * @return
			 */
				public String allInfor(String select)
			{
				String result=null,change=null;
				StringBuffer br=new StringBuffer();
			     try {
					    PreparedStatement sql;
						Connection dbConnection=conDatabase();
					    ResultSet rs=null;
						sql=dbConnection.prepareStatement(select);
						rs=sql.executeQuery();
						int i=0; // get the encode
						br.append("<style type=\"text/css\">");
						br.append("span{color:red; font_size:30px;}</style>");						
						while(rs.next()){
							  i++;
							  br.append("</br>"+i+".name: "+rs.getString(2)+"</br>");
							  br.append("description: "+rs.getString(3)+"</br>");
							  select="select * from category where category_id='"+rs.getString(4)+"';";
							  change=getOneValue(select,2);							  
							 //change id to the category name. 
						      br.append("category: "+change+"</br>");
						      br.append("size: "+rs.getString(5)+"</br>");
						      br.append("weight: "+rs.getString(6)+"</br>");
						      br.append("price: "+rs.getString(7)+"</br>");
						      int quan=Integer.parseInt(rs.getString(8));
						      int quanM=Integer.parseInt(rs.getString(9));
						      if(quan<=quanM&&quan!=0)
						      {
						    	  br.append("<form action=\"addQ\" method=\"POST\">"
						    			+"<span>quantity: "+rs.getString(8)
						    	  		+ " the quantity reach the low quantity. please update: </span>"
						    	  		+ "<input type=\"text\" name=\"1\"> " 
						    	  		+ "<input type=\"submit\" name=\"2\" value=\"update\">"
						    	  		+ "<input type=\"hidden\" name=\"3\" value=\""+rs.getString(1)+"\">"
						    	  		+ "</form>");
						      }
						      if(quan==0)
						      {
						    	  br.append("<form action=\"addQ\" method=\"POST\">"
							    			+"<span>quantity: "+rs.getString(8)
							    	  		+ " the quantity reach to 0. please update: </span>"
							    	  		+ "<input type=\"text\" name=\"1\"> " 
							    	  		+ "<input type=\"submit\" name=\"2\" value=\"update\">"
							    	  		+"<input type=\"hidden\" name=\"3\" value=\""+rs.getString(1)+"\">"
							    	  		+ "</form>");
						      }
						      if(quan>quanM)
						      {	  
						      br.append("quantity: "+rs.getString(8)+"</br>");
						      }
						      br.append("min quantity: "+rs.getString(9)+"</br>");
						      br.append("origin: "+rs.getString(10)+"</br>");
						      select="select * from region where region_id='"+rs.getString(13)+"';";
							  change=getOneValue(select,2);	
							  // change id to the region name
							  br.append("region: "+change+"</br>");
							  change=rs.getString(11);
							  change=change.substring(change.length()-6,change.length()); 
							  // get the url for the image.
							  br.append("picture: </br><input type=\"image\" src=\""+change+"\"><br>");
							  br.append("<form action=\"balance\" method=\"POST\">");
							  br.append("<input type=\"submit\" value=\"buy\"  style=\"font-size:20px;\">");
							  br.append("<input type=\"hidden\" value=\""+rs.getString(1)+"\" name=\"10\"></form>");
						   }
			    		  result=br.toString();
						  br.setLength(0);
					} catch (SQLException | ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					  
					
					return result;
			}
				
				
		
				/**
				 * show the whole page for employee to show all products belong to his warehouse 
				 * 
				 * @param select
				 * @return
				 */
					public String allInforE(String select,String select1)
				{
					String result=null,change=null;
					StringBuffer br=new StringBuffer();
					String balance=null;
					double balanceC=0;
				     try {
						    PreparedStatement sql;
							Connection dbConnection=conDatabase();
						    ResultSet rs=null;
						    sql=dbConnection.prepareStatement(select1);
						    rs=sql.executeQuery();
			/*			    while(rs.next())
						    {
						        balance=rs.getString(12);
						    }
						    print.pl("blance:"+balance);
						    balanceC=Double.parseDouble(balance);
				*/		    
							sql=dbConnection.prepareStatement(select);
							rs=sql.executeQuery();
							br.append("<style type=\"text/css\">");
							br.append("span{color:red; font_size:30px;}</style>");		
							int i=0; // get the encode				
							while(rs.next()){
								  i++;
								  br.append("</br>"+i+".name: "+rs.getString(2)+"</br>");
								  br.append("description: "+rs.getString(3)+"</br>");
								  select="select * from category where category_id='"+rs.getString(4)+"';";
								  change=getOneValue(select,2);							  
								 //change id to the category name. 
							      br.append("category: "+change+"</br>");
							      br.append("size: "+rs.getString(5)+"</br>");
							      br.append("weight: "+rs.getString(6)+"</br>");
							      br.append("price: "+rs.getString(7)+"</br>");	 
							      int quan=Integer.parseInt(rs.getString(8));
							      int quanM=Integer.parseInt(rs.getString(9));
							      if(quan<=quanM&&quan!=0)
							      {
							    	  br.append("<form action=\"addQE\" method=\"POST\">"
							    			+"<span>quantity: "+rs.getString(8)
							    	  		+ " the quantity reach the low quantity. please update: </span>"
							    	  		+ "<input type=\"text\" name=\"1\"> " 
							    	  		+ "<input type=\"submit\" name=\"2\" value=\"update\">"
							    	  		+ "<input type=\"hidden\" name=\"3\" value=\""+rs.getString(1)+"\">"
							    	  		+ "</form>");
							    	  br.append("<span>status: Low stock</span></br>"); 
							      }
							      if(quan==0)
							      {
							    	  br.append("<form action=\"addQE\" method=\"POST\">"
								    			+"<span>quantity: "+rs.getString(8)
								    	  		+ " the quantity reach to 0. please update: </span>"
								    	  		+ "<input type=\"text\" name=\"1\"> " 
								    	  		+ "<input type=\"submit\" name=\"2\" value=\"update\">"
								    	  		+"<input type=\"hidden\" name=\"3\" value=\""+rs.getString(1)+"\">"
								    	  		+ "</form>");
							    	  
							    	  br.append("<span>status: out of stock</span></br>"); 
							      }
							      if(quan>quanM)
							      {	  
							      br.append("quantity: "+rs.getString(8)+"</br>");
							      br.append("status: in stock</br>"); 
							      }
	//  String yue =rs.getString(11); yue=rs.getString(8)*rs.getString(7)-price;
							     //br.append("cash:"+yue+"</br>"); 
	//						      br.append("status: Low stock</br>");
							      br.append("min quantity: "+rs.getString(9)+"</br>");
							      br.append("origin: "+rs.getString(10)+"</br>");
							      select="select * from region where region_id='"+rs.getString(13)+"';";
								  change=getOneValue(select,2);	
								  // change id to the region name
								  br.append("region: "+change+"</br>");
								  change=rs.getString(11);
								  change=change.substring(change.length()-6,change.length()); 
								  // get the url for the image.
								  br.append("picture: </br><input type=\"image\" src=\""+change+"\"><br>");
								  br.append("<form action=\"EviewA\" method=\"GET\">");
								  br.append("<input type=\"submit\" value=\"ship_order\"  style=\"font-size:20px;\">");
								  br.append("<input type=\"hidden\" value=\""+rs.getString(1)+"\" name=\"10\"></form>");
								  //br.append.........list...
							   }
				    		  result=br.toString();
							  br.setLength(0);
						} catch (SQLException | ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						  
				    	return result;
				}
				
					
					
					/**
					 * show the whole page for employee to show all his warehouse 
					 * 
					 * @param select
					 * @return
					 */
						public String allInforEW(String select)
					{
						String result=null,change=null;
						StringBuffer br=new StringBuffer();
					     try {
							    PreparedStatement sql;
								Connection dbConnection=conDatabase();
							    ResultSet rs=null;
								sql=dbConnection.prepareStatement(select);
								rs=sql.executeQuery();	
								int i=0; // get the encode	
								br.append("<style type=\"text/css\">");
								br.append("span{color:blue; font_size:20px;}</style>");	
								while(rs.next()){
									  i++;
									  br.append("</br>"+i+". warehouse_name: <span>"+rs.getString(2)+"</span></br>");
							          select="select * from region where region_id='"+rs.getString(3)+"';";
							          change=getOneValue(select,2);
							          //get the region_name
								      br.append("warehouse_region: <span>"+change+"</span></br>");
								      select="select * from Employee where employee_id='"+rs.getString(4)+"';"; 
								      change=getOneValue(select,2);	
								      // get the customer name
								      br.append("warehouse_Employee_username_name: <span>"+change+"</span></br>");
								      br.append("warehouse_Employee_email: <span>"+rs.getString(5)+"</span></br>");
								      br.append("warehouse_Employee_phone: <span>"+rs.getString(6)+"</span></br>");
								      br.append("warehouse_address: <span>"+rs.getString(7)+"</span></br>");	 
									  br.append("<form action=\"EviewA\" method=\"POST\">");
									  br.append("<input type=\"submit\" value=\"show_product\"  style=\"font-size:20px;\">");
									  br.append("<input type=\"hidden\" value=\""+rs.getString(2)+"\" name=\"2\"></form>");								  
								   }
					    		  result=br.toString();
								  br.setLength(0);
							} catch (SQLException | ClassNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							  
					    	return result;
					}
					
						
						
						

}
