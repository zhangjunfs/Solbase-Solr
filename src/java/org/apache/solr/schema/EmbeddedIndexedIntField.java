package org.apache.solr.schema;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.lucene.search.SortField;


public class EmbeddedIndexedIntField extends IntField {
	int fieldNumber = 0;
	
	Map<String,Integer> strToIntMap = new HashMap<String,Integer>();
	
	protected void init(IndexSchema schema, Map<String, String> args) {
		super.init(schema, args);
		
		fieldNumber = Integer.parseInt(args.remove("fieldNumber"));
		
		Iterator<String> itr = args.keySet().iterator();
		List<String> keysToDelete = new ArrayList<String>();
		
		while(itr.hasNext()){
			String key = itr.next();
			String value = args.get(key);
			try {
				if(key.indexOf("type_") == 0){
					keysToDelete.add(key);
					key = key.substring(5);
					int intKey = Integer.parseInt(key);

					strToIntMap.put(value, intKey);
				}
			} catch (NumberFormatException e){
			
			}
		}
		for(String key: keysToDelete){
			args.remove(key);
		}
		
	}

	public SortField getSortField(SchemaField field, boolean reverse) {
		return new EmbeddedSortField(field.getName(), SortField.INT, reverse, fieldNumber);
	}
	
	public int getFieldNumber(){
		return this.fieldNumber;
	}
	
	public int getStringToInt(String term){
		// parsing out actual data from term
		String val = term.substring(term.indexOf(":") + 1);
		try {
			int ret = Integer.parseInt(val);
			return ret;
		} catch(NumberFormatException e){
			// incase data is not integer, try to see if there is any string to integer mapping defined
			// from schema fieldType
			if(strToIntMap.containsKey(term)){
				return strToIntMap.get(term);
			}			
		}
		return -1;
	}
}
