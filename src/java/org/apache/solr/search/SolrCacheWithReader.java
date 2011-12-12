package org.apache.solr.search;

import java.util.Set;

import org.apache.lucene.index.IndexReader;

public interface SolrCacheWithReader {
	public void setReader(IndexReader reader);
	
	  public Object put(Object key, Object value, Set terms);

	  public Object get(Object key, Set terms);

	  public void acquireLock(Set terms);

	  public void releaseLock(Set terms);
}
