package com.td.storecalc;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import org.apache.http.client.config.*;

public class ExpandableListDataPump {
    public static LinkedHashMap<String, List<String>> getData() {
        LinkedHashMap<String, List<String>> expandableListDetail = new LinkedHashMap<String, List<String>>();
        
		//Begin Items in Categories
		List<String> legal = new ArrayList<String>();
		legal.add(String.format("%-30s $%5s", "Envelope", "0.03"));
		legal.add(String.format("%-30s $%5s", "White Sketch Pad", "1.22"));
		legal.add(String.format("%-30s $%5s", "Clasp Envelope", "0.21"));
		legal.add(String.format("%-30s $%5s", "Legal Pad", "1.44"));
		legal.add(String.format("%-30s $%5s", "Ink Pen", "0.20"));
		legal.add(String.format("%-30s $%5s", "#2 Pencil", "0.15"));
		legal.add(String.format("%-30s $%5s", "Crazy Art Pencils", "1.95"));
		legal.add(String.format("%-30s $%5s", "Stamps", "0.60"));

		List<String> cards = new ArrayList<String>();
		cards.add(String.format("%-30s $%5s", "Thinking of You", "0.85"));
		cards.add(String.format("%-30s $%5s", "I Love You", "0.85"));
		cards.add(String.format("%-30s $%5s", "Birthday", "0.85"));

		List<String> drinks = new ArrayList<String>();
		drinks.add(String.format("%-30s $%5s", "Coca Cola", "1.55"));
		drinks.add(String.format("%-30s $%5s", "Diet Coke", "1.55"));
		drinks.add(String.format("%-30s $%5s", "Dr Pepper", "1.55"));
		drinks.add(String.format("%-30s $%5s", "Mountain Dew", "1.55"));
		drinks.add(String.format("%-30s $%5s", "GCS Coffee 3oz", "4.52"));
		drinks.add(String.format("%-30s $%5s", "GCS Coffee 8oz", "8.83"));
		drinks.add(String.format("%-30s $%5s", "Folgers Coffee", "9.49"));
		drinks.add(String.format("%-30s $%5s", "Fruit Punch Mix", "3.17"));
		drinks.add(String.format("%-30s $%5s", "Lemonade Mix", "3.17"));
		drinks.add(String.format("%-30s $%5s", "Peach  Mix", "3.17"));
		drinks.add(String.format("%-30s $%5s", "Artificial Sweetener", "2.18"));
		drinks.add(String.format("%-30s $%5s", "Hot Chocolate", "3.07"));
		drinks.add(String.format("%-30s $%5s", "Tea Bags", "1.72"));
		drinks.add(String.format("%-30s $%5s", "Sugar", "2.18"));
		drinks.add(String.format("%-30s $%5s", "Creamer", "2.71"));
		drinks.add(String.format("%-30s $%5s", "Sprite", "1.55"));
		
		List<String> chips = new ArrayList<String>();
		chips.add(String.format("%-30s $%5s", "Hot Pork Skins", "1.70"));
		chips.add(String.format("%-30s $%5s", "Wize Cheez Doodles", "1.55"));
		chips.add(String.format("%-30s $%5s", "Tostidos", "1.29"));
		chips.add(String.format("%-30s $%5s", "Voo Doo Chips", "1.03"));
		chips.add(String.format("%-30s $%5s", "Honey BBQ Chips", "1.85"));
		chips.add(String.format("%-30s $%5s", "BBQ Corn Chips", "3.10"));
		chips.add(String.format("%-30s $%5s", "Cheetos Jalapeno", "0.93"));

		List<String> soups = new ArrayList<String>();
		soups.add(String.format("%-30s $%5s", "Beef Soup", "0.46"));
		soups.add(String.format("%-30s $%5s", "Picante Chicken", "0.46"));
		soups.add(String.format("%-30s $%5s", "Chili Soup", "0.46"));
		soups.add(String.format("%-30s $%5s", "Creamy Chicken Soup", "0.46"));
		soups.add(String.format("%-30s $%5s", "Shrimp Soup", "0.46"));
		
        List<String> snacks = new ArrayList<String>();
        snacks.add(String.format("%-30s $%5s", "Refried Beans", "2.34"));
		snacks.add(String.format("%-30s $%5s", "Rice", "3.04"));
		snacks.add(String.format("%-30s $%5s", "Hot Salsa", "2.40"));
		snacks.add(String.format("%-30s $%5s", "Flour Tortillas", "1.19"));
		snacks.add(String.format("%-30s $%5s", "Peanuts", "0.62"));
		snacks.add(String.format("%-30s $%5s", "Kosher Pickle", "0.97"));
		snacks.add(String.format("%-30s $%5s", "Summer Sausage", "2.22"));
		snacks.add(String.format("%-30s $%5s", "Spam", "2.01"));
		snacks.add(String.format("%-30s $%5s", "Tuna", "2.65"));
		snacks.add(String.format("%-30s $%5s", "Sweet Sue Ham", "4.38"));
		snacks.add(String.format("%-30s $%5s", "Hot Chili w/ Beans", "3.41"));
		snacks.add(String.format("%-30s $%5s", "Kraft Jal Squeeze Cheese", "4.62"));
		snacks.add(String.format("%-30s $%5s", "Peanut Butter 1.12oz", "1.00"));
		snacks.add(String.format("%-30s $%5s", "Instant Oatmeal", "3.82"));
		snacks.add(String.format("%-30s $%5s", "Sweet Sue Chicken", "2.90"));
		snacks.add(String.format("%-30s $%5s", "Grits", "3.16"));
		snacks.add(String.format("%-30s $%5s", "S'Berry Pop Tarts", "1.03"));
		snacks.add(String.format("%-30s $%5s", "Beef & Cheese Sticks", "0.67"));
		snacks.add(String.format("%-30s $%5s", "Nature Valley Granola Bar", "0.67"));
		snacks.add(String.format("%-30s $%5s", "Mackerel Filets", "1.51"));
		snacks.add(String.format("%-30s $%5s", "Honey Pepper Turkey Stick", "2.27"));
		
		List<String> cookies = new ArrayList<String>();
		cookies.add(String.format("%-30s $%5s", "Duplex Cookies", "1.80"));
		cookies.add(String.format("%-30s $%5s", "Strawberry Cookies", "1.80"));
		cookies.add(String.format("%-30s $%5s", "Vanilla Wafers", "1.52"));
		
		List<String> cakes = new ArrayList<String>();
		cakes.add(String.format("%-30s $%5s", "Lil Debbie Star Crunch", "2.19"));
		cakes.add(String.format("%-30s $%5s", "Lil Debbie Oatmeal Pies", "2.19"));
		cakes.add(String.format("%-30s $%5s", "Lil Debbie Nutty Bars", "2.19"));
		cakes.add(String.format("%-30s $%5s", "Chocolate Cup Cakes", "0.89"));
		cakes.add(String.format("%-30s $%5s", "Glazed Honey Bun", "1.40"));
		
		List<String> crackers = new ArrayList<String>();
		crackers.add(String.format("%-30s $%5s", "Snack Crackers", "2.28"));
		crackers.add(String.format("%-30s $%5s", "Saltine Crackers", "3.00"));
		crackers.add(String.format("%-30s $%5s", "Grilled Cheese Crackers", "0.51"));
		
		List<String> condiments = new ArrayList<String>();
		condiments.add(String.format("%-30s $%5s", "Ranch Dip", "0.65"));
		condiments.add(String.format("%-30s $%5s", "Squeeze Grape Jelly", "2.67"));
		condiments.add(String.format("%-30s $%5s", "Squeeze Mayo", "2.45"));
		condiments.add(String.format("%-30s $%5s", "Salt Packets", "0.56"));
		condiments.add(String.format("%-30s $%5s", "Jalepeno Peppers", "2.86"));
		condiments.add(String.format("%-30s $%5s", "Hot Sauce", "1.50"));
		
		List<String> iceCream = new ArrayList<String>();
		iceCream.add(String.format("%-30s $%5s", "Vanilla", "2.85"));
		iceCream.add(String.format("%-30s $%5s", "Chocolate", "2.85"));
		iceCream.add(String.format("%-30s $%5s", "Cookies and Cream", "2.85"));
		iceCream.add(String.format("%-30s $%5s", "Butter Pecan", "2.85"));
		
		List<String> candy = new ArrayList<String>();
		candy.add(String.format("%-30s $%5s", "Reeses Cup", "1.05"));
		candy.add(String.format("%-30s $%5s", "3 Musketeers", "1.05"));
		candy.add(String.format("%-30s $%5s", "Snickers", "1.05"));
		candy.add(String.format("%-30s $%5s", "Kit Kat", "1.05"));
		candy.add(String.format("%-30s $%5s", "Sour Balls", "0.90"));
		candy.add(String.format("%-30s $%5s", "Jolly Ranchers", "1.27"));
		candy.add(String.format("%-30s $%5s", "Starlight Mints", "0.95"));
		candy.add(String.format("%-30s $%5s", "Butterscotch Buttons", "0.85"));
		candy.add(String.format("%-30s $%5s", "Sugar Free Candy", "0.90"));
		candy.add(String.format("%-30s $%5s", "Trail Mix Unsalted", "0.67"));
		candy.add(String.format("%-30s $%5s", "Trail Mix Nut N' Yogurt", "0.93"));
		
		List<String> otcDrugs = new ArrayList<String>();
		otcDrugs.add(String.format("%-30s $%5s", "Acetaminophen Tablets", "1.55"));
		otcDrugs.add(String.format("%-30s $%5s", "Allergy Tablets", "1.35"));
		otcDrugs.add(String.format("%-30s $%5s", "Ibuprofen", "1.85"));
		otcDrugs.add(String.format("%-30s $%5s", "Hydrocortisone Cream", "2.57"));
		otcDrugs.add(String.format("%-30s $%5s", "Dandruff Shampoo", "1.57"));		
		otcDrugs.add(String.format("%-30s $%5s", "Tolnaftate Anti-Fungal", "1.91"));
		otcDrugs.add(String.format("%-30s $%5s", "Cough Drops", "1.10"));
		otcDrugs.add(String.format("%-30s $%5s", "Eye Drops", "1.71"));
		otcDrugs.add(String.format("%-30s $%5s", "Oral Pain Relief", "3.52"));
		otcDrugs.add(String.format("%-30s $%5s", "Benzoyl Peroxide Gel", "1.90"));
		
		List<String> batteries = new ArrayList<String>();
		batteries.add(String.format("%-30s $%5s", "AA - 4 Pack", "3.95"));
		batteries.add(String.format("%-30s $%5s", "AAA - 4 Pack", "3.95"));
		
		List<String> accessories = new ArrayList<String>();
		accessories.add(String.format("%-30s $%5s", "Ear Buds Clear", "4.60"));
		accessories.add(String.format("%-30s $%5s", "Ear Buds", "1.60"));
		accessories.add(String.format("%-30s $%5s", "Calculator", "4.88"));
		
		List<String> hygiene = new ArrayList<String>();
		hygiene.add(String.format("%-30s $%5s", "Roll On Deodorant", "1.83"));
		hygiene.add(String.format("%-30s $%5s", "Degree Antiperspirant", "2.69"));
		hygiene.add(String.format("%-30s $%5s", "Mennen Speed Stick", "2.79"));
		hygiene.add(String.format("%-30s $%5s", "Dove Soap", "1.90"));
		hygiene.add(String.format("%-30s $%5s", "Dial Soap", "1.05"));
		hygiene.add(String.format("%-30s $%5s", "Ivory Soap", "0.70"));
		hygiene.add(String.format("%-30s $%5s", "Irish Spring", "1.05"));
		hygiene.add(String.format("%-30s $%5s", "Close-Up Toothpaste", "1.88"));
		hygiene.add(String.format("%-30s $%5s", "Colgate Toothpaste", "2.54"));
		hygiene.add(String.format("%-30s $%5s", "Indv All in One Bodywash", "0.20"));
		hygiene.add(String.format("%-30s $%5s", "Sensodyne Toothpaste", "4.06"));
		hygiene.add(String.format("%-30s $%5s", "Dental Floss Loops", "2.20"));
		hygiene.add(String.format("%-30s $%5s", "Denture Tablets - 40ct", "3.45"));
		hygiene.add(String.format("%-30s $%5s", "Mouthwash", "0.75"));
		hygiene.add(String.format("%-30s $%5s", "Fixodent", "4.61"));
		hygiene.add(String.format("%-30s $%5s", "Toothbrush", "0.65"));
		
		List<String> skin = new ArrayList<String>();
		skin.add(String.format("%-30s $%5s", "Cocoa Butter Lotion", "1.75"));
		skin.add(String.format("%-30s $%5s", "Baby Oil", "1.30"));
		skin.add(String.format("%-30s $%5s", "Suave Therapy Lotion", "2.85"));
		skin.add(String.format("%-30s $%5s", "Cocoa Butter Stick", "2.50"));
		skin.add(String.format("%-30s $%5s", "Noxzema", "2.10"));
		skin.add(String.format("%-30s $%5s", "Ambi Complexion Soap", "2.45"));
		skin.add(String.format("%-30s $%5s", "Sunblock", "4,61"));
		skin.add(String.format("%-30s $%5s", "Chapstick", "1.47"));
		skin.add(String.format("%-30s $%5s", "Petroleum Jelly", "1.52"));
		skin.add(String.format("%-30s $%5s", "Baby Powder", "0.88"));
		
		List<String> shaving = new ArrayList<String>();
		shaving.add(String.format("%-30s $%5s", "Aftershave", "1.57"));
	
		List<String> hairCare = new ArrayList<String>();
		hairCare.add(String.format("%-30s $%5s", "Blue Magic Hair", "2.40"));
		hairCare.add(String.format("%-30s $%5s", "Murray's Pomade", "3.55"));
		hairCare.add(String.format("%-30s $%5s", "Afro Pick", "0.25"));
		hairCare.add(String.format("%-30s $%5s", "Hair Brush", "1.32"));
		hairCare.add(String.format("%-30s $%5s", "Comb", "0.10"));
		hairCare.add(String.format("%-30s $%5s", "Suave Shampoo+Cond", "3.14"));
		hairCare.add(String.format("%-30s $%5s", "Indv Shampoo .35oz", "0.20"));
		hairCare.add(String.format("%-30s $%5s", "Crawford Dandruff Shampoo", "2.20"));
		
		List<String> misc = new ArrayList<String>();
		misc.add(String.format("%-30s $%5s", "Cup - Large W/Lid", "0.60"));
		misc.add(String.format("%-30s $%5s", "Insulated Mug W/Lid", "2.50"));
		misc.add(String.format("%-30s $%5s", "Cereal Bowl W/Lid", "1.45"));
		misc.add(String.format("%-30s $%5s", "Playing Cards", "1.79"));
		misc.add(String.format("%-30s $%5s", "Ear Plugs", "0.30"));
		misc.add(String.format("%-30s $%5s", "Mirror", "1.93"));
		misc.add(String.format("%-30s $%5s", "Q-Tips", "1.80"));
		misc.add(String.format("%-30s $%5s", "Toothbrush Holder", "0.40"));
		misc.add(String.format("%-30s $%5s", "Soap Dish", "0.50"));
		misc.add(String.format("%-30s $%5s", "Shower Shoes - 2XL", "1..60"));
		misc.add(String.format("%-30s $%5s", "Shower Shoes - 3XL", "1.96"));
		misc.add(String.format("%-30s $%5s", "Athletic Shorts -Lrg ", "13.46"));
		misc.add(String.format("%-30s $%5s", "Athletic Shorts -X Lrg ", "14.60"));
		misc.add(String.format("%-30s $%5s", "Athletic Shorts - 2X ", "14.60"));
		misc.add(String.format("%-30s $%5s", "Athletic Shorts - 3X ", "14.60"));
		misc.add(String.format("%-30s $%5s", "Athletic Shorts - 4X ", "15.80"));
		misc.add(String.format("%-30s $%5s", "Athletic Shorts - 5X ", "15.80"));
		misc.add(String.format("%-30s $%5s", "Athletic Shorts - 6X ", "15.80"));
		misc.add(String.format("%-30s $%5s", "Reading Glasses 1.5", "4.73"));
		misc.add(String.format("%-30s $%5s", "Reading Glasses 2.5", "4.73"));
		//End Items in Categories
		
		//Category List and Insertion of Items
        expandableListDetail.put("Legal Supplies", legal);
        expandableListDetail.put("Greeting Cards", cards);
        expandableListDetail.put("Drinks", drinks);
		expandableListDetail.put("Chips", chips);
		expandableListDetail.put("Soups", soups);
		expandableListDetail.put("Assorted Snacks", snacks);
		expandableListDetail.put("Cookies", cookies);
        expandableListDetail.put("Snack Cakes", cakes);
		expandableListDetail.put("Crackers", crackers);
		expandableListDetail.put("Condiments", condiments);
        expandableListDetail.put("Ice Cream", iceCream);
		expandableListDetail.put("Candy", candy);
		expandableListDetail.put("OTC Drugs", otcDrugs);
        expandableListDetail.put("Batteries", batteries);
		expandableListDetail.put("Accessories", accessories);
        expandableListDetail.put("Hygiene", hygiene);
        expandableListDetail.put("Skin Care", skin);
        expandableListDetail.put("OTC Drugs", shaving);
        expandableListDetail.put("OTC Drugs", hairCare);
        expandableListDetail.put("Miscellaneous", misc);
        return expandableListDetail;
    }
}
