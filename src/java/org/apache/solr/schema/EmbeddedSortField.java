package org.apache.solr.schema;

import java.io.IOException;
import java.io.Reader;
import java.util.Locale;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.document.Fieldable;
import org.apache.lucene.search.EmbeddedFieldComparator;
import org.apache.lucene.search.FieldCache;
import org.apache.lucene.search.FieldComparator;
import org.apache.lucene.search.FieldComparatorSource;
import org.apache.lucene.search.SortComparatorSource;
import org.apache.lucene.search.SortField;

@SuppressWarnings("deprecation")
public class EmbeddedSortField extends SortField implements Fieldable {

	private static final long serialVersionUID = -1183402498125438208L;
	
	private int fieldNumber = 0;

	public EmbeddedSortField(String field) {
		super(field);
	}

	public EmbeddedSortField(String field, boolean reverse) {
		super(field, reverse);
	}

	public EmbeddedSortField(String field, int type) {
		super(field, type);
	}

	public EmbeddedSortField(String field, int type, boolean reverse) {
		super(field, type, reverse);
	}
	
	public EmbeddedSortField(String field, int type, boolean reverse, int fieldNumber) {
		super(field, type, reverse);
		this.setFieldNumber(fieldNumber);
	}

	@Override
	public FieldComparator getComparator(int numHits, int sortPos) throws IOException {
		String field = getField();
		Locale locale = getLocale();
		int type = getType();
		boolean reverse = getReverse();
		FieldCache.Parser parser = getParser();
		
	    if (locale != null) {
	        // TODO: it'd be nice to allow FieldCache.getStringIndex
	        // to optionally accept a Locale so sorting could then use
	        // the faster StringComparator impls
	        return new EmbeddedFieldComparator.StringComparatorLocale(numHits, field, locale);
	      }

	      switch (getType()) {
	      case SortField.SCORE:
	        return new EmbeddedFieldComparator.RelevanceComparator(numHits);

	      case SortField.DOC:
	        return new EmbeddedFieldComparator.DocComparator(numHits);

	      case SortField.INT:
	        return new EmbeddedFieldComparator.IntComparator(numHits, field, parser, getFieldNumber());

	      case SortField.FLOAT:
	        return new EmbeddedFieldComparator.FloatComparator(numHits, field, parser);

	      case SortField.LONG:
	        return new EmbeddedFieldComparator.LongComparator(numHits, field, parser);

	      case SortField.DOUBLE:
	        return new EmbeddedFieldComparator.DoubleComparator(numHits, field, parser);

	      case SortField.BYTE:
	        return new EmbeddedFieldComparator.ByteComparator(numHits, field, parser);

	      case SortField.SHORT:
	        return new EmbeddedFieldComparator.ShortComparator(numHits, field, parser);

	      case SortField.CUSTOM:
	    	FieldComparatorSource comparatorSource = getComparatorSource();
	        assert getFactory() == null && comparatorSource != null;
	        return comparatorSource.newComparator(field, numHits, sortPos, reverse);

	      case SortField.STRING:
	        return new FieldComparator.StringOrdValComparator(numHits, field, sortPos, reverse);

	      case SortField.STRING_VAL:
	        return new EmbeddedFieldComparator.StringValComparator(numHits, field);
	          
	      default:
	        throw new IllegalStateException("Illegal sort type: " + type);
	      }
	}

	public EmbeddedSortField(String field, FieldCache.Parser parser) {
		super(field, parser);
	}

	public EmbeddedSortField(String field, FieldCache.Parser parser, boolean reverse) {
		super(field, parser, reverse);
	}

	public EmbeddedSortField(String field, Locale locale) {
		super(field, locale);
	}

	public EmbeddedSortField(String field, Locale locale, boolean reverse) {
		super(field, locale, reverse);
	}

	public EmbeddedSortField(String field, SortComparatorSource comparator) {
		super(field, comparator);
	}

	public EmbeddedSortField(String field, FieldComparatorSource comparator) {
		super(field, comparator);
	}

	public EmbeddedSortField(String field, SortComparatorSource comparator, boolean reverse) {
		super(field, comparator, reverse);
	}

	public EmbeddedSortField(String field, FieldComparatorSource comparator, boolean reverse) {
		super(field, comparator, reverse);
	}

	public void setFieldNumber(int fieldNumber) {
		this.fieldNumber = fieldNumber;
	}

	public int getFieldNumber() {
		return fieldNumber;
	}

	@Override
	public void setBoost(float boost) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float getBoost() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String stringValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reader readerValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] binaryValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TokenStream tokenStreamValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isStored() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isIndexed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isTokenized() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCompressed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isTermVectorStored() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isStoreOffsetWithTermVector() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isStorePositionWithTermVector() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isBinary() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getOmitNorms() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setOmitNorms(boolean omitNorms) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setOmitTf(boolean omitTf) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean getOmitTf() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isLazy() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getBinaryOffset() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getBinaryLength() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public byte[] getBinaryValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] getBinaryValue(byte[] result) {
		// TODO Auto-generated method stub
		return null;
	}

}
