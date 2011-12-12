package org.apache.solr.search;

import org.apache.lucene.search.EmbeddedFieldFilter;
import org.apache.lucene.search.EmbeddedMultiFieldsFilter;
import org.apache.lucene.search.EmbeddedRangeFieldFilter;
import org.apache.lucene.search.EmbeddedTermFieldFilter;
import org.apache.lucene.search.Filter;
import org.apache.lucene.util.OpenBitSet;

public class EmbeddedDocSet implements DocSet {

	private EmbeddedMultiFieldsFilter emff;
	
	public EmbeddedDocSet(){
		emff = new EmbeddedMultiFieldsFilter();
	}
	// adding embedded range filter
	public void addFilter(int start, int end, int fieldNumber){
		emff.addFilter(new EmbeddedRangeFieldFilter(start, end, fieldNumber));
	}
	
	// adding embedded term filter
	public void addFilter(int val, int fieldNumber){
		emff.addFilter(new EmbeddedTermFieldFilter(val, fieldNumber));
	}
	@Override
	public void add(int doc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addUnique(int doc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean exists(int docid) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public DocIterator iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OpenBitSet getBits() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long memSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public DocSet intersection(DocSet other) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int intersectionSize(DocSet other) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public DocSet union(DocSet other) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int unionSize(DocSet other) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public DocSet andNot(DocSet other) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int andNotSize(DocSet other) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Filter getTopFilter() {
		return this.emff;
	}

	public boolean isEmptyFilter(){
		return this.emff.isEmptyFilter();
	}
}
