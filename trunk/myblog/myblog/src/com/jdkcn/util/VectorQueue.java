/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, jdkcn.com
 * Filename:		VectorQueue.java
 * Class:			VectorQueue
 * Date:			2006-9-21
 * Author:			<a href="mailto:rory.cn@gmail.com">somebody</a>
 * Description:		
 *
 *
 * ======================================================================
 * Change History Log
 * ----------------------------------------------------------------------
 * Mod. No.	| Date		| Name			| Reason			| Change Req.
 * ----------------------------------------------------------------------
 * 			| 2006-9-21   | Rory Ye	    | Created			|
 *
 * </pre>
 **/
package com.jdkcn.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Vector;

/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since 2006-9-21
 * @version $Id VectorQueue.java$
 */
public class VectorQueue<T> implements Queue<T> {
	private List<T> elements = new Vector<T>();

	/**
	 * 如果可能，将指定的元素插入此队列。
	 * @param obj
	 * @return 如果可以向此队列添加元素，则返回 true；否则返回 false。
	 */
	public boolean offer(T t) {
		return elements.add(t);
	}

	/**
	 * 检索并移除此队列的头，如果此队列为空，则返回 null。
	 */
	public T poll() {
		if(elements.isEmpty())
			return null;
		else{
			T obj = elements.get(0);
			elements.remove(0);
			return obj;
		}

	}

	/**
	 * 检索并移除此队列的头。此方法与 poll 方法的不同在于，如果此队列为空，它会抛出一个异常。 
	 * @throws java.util.NoSuchElementException 如果队列为空
	 */
	public T remove() {
		T obj = null;
		if(elements.isEmpty())
			throw new NoSuchElementException("Vetory Queue is empty,No Elements contained.");
		else{
			obj = elements.get(0);
			elements.remove(0);
		}
		return obj;
	}

	/**
	 * 检索，但是不移除此队列的头，如果此队列为空，则返回 null。 
	 */
	public T peek() {
		if(elements.isEmpty())
			return null;
		else{
			return elements.get(0);
		}
	}

	/**
	 * 检索，但是不移除此队列的头。此方法与 peek 方法的惟一不同是，如果此队列为空，它会抛出一个异常。 
	 * @throws java.util.NoSuchElementException 如果队列为空 
	 */
	public T element() {
		if(elements.isEmpty())
			throw new NoSuchElementException("Vetory Queue is empty,No Elements contained.");
		else{
			return elements.get(0);
		}
	}
	
	/**
	 * 打印VectorQueue里的元素
	 *
	 */
	public void printQueue() {
		if(elements.isEmpty()){
			System.out.println("VectorQueue is Empty.");
		}else{
			System.out.println("There are ["+elements.size()+"] Elements in VectorQuee.\n");
			for(int i=0, n=elements.size();i<n;i++){
				System.out.println(i+":"+elements.get(i)+"\n");
			}
		}
	}

	/* (non-Javadoc)
	 * @see java.util.Collection#size()
	 */
	public int size() {
		return elements.size();
	}

	/* (non-Javadoc)
	 * @see java.util.Collection#isEmpty()
	 */
	public boolean isEmpty() {
		return elements.isEmpty();
	}

	/* (non-Javadoc)
	 * @see java.util.Collection#contains(java.lang.Object)
	 */
	public boolean contains(Object o) {
		return elements.contains(o);
	}

	/* (non-Javadoc)
	 * @see java.util.Collection#iterator()
	 */
	public Iterator<T> iterator() {
		return elements.iterator();
	}

	/* (non-Javadoc)
	 * @see java.util.Collection#toArray()
	 */
	public Object[] toArray() {
		return elements.toArray();
	}

	/* (non-Javadoc)
	 * @see java.util.Collection#toArray(java.lang.Object[])
	 */
	@SuppressWarnings("hiding")
	public <T> T[] toArray(T[] arg0) {
		return elements.toArray(arg0);
	}

	/* (non-Javadoc)
	 * @see java.util.Collection#add(java.lang.Object)
	 */
	public boolean add(T obj) {
		return elements.add(obj);
	}

	/* (non-Javadoc)
	 * @see java.util.Collection#remove(java.lang.Object)
	 */
	public boolean remove(Object o) {
		return elements.remove(o);
	}

	/* (non-Javadoc)
	 * @see java.util.Collection#containsAll(java.util.Collection)
	 */
	public boolean containsAll(Collection<?> arg0) {
		return elements.containsAll(arg0);
	}

	/* (non-Javadoc)
	 * @see java.util.Collection#addAll(java.util.Collection)
	 */
	public boolean addAll(Collection<? extends T> arg0) {
		return elements.addAll(arg0);
	}

	/* (non-Javadoc)
	 * @see java.util.Collection#removeAll(java.util.Collection)
	 */
	public boolean removeAll(Collection<?> arg0) {
		return elements.removeAll(arg0);
	}

	/* (non-Javadoc)
	 * @see java.util.Collection#retainAll(java.util.Collection)
	 */
	public boolean retainAll(Collection<?> arg0) {
		return elements.retainAll(arg0);
	}

	/* (non-Javadoc)
	 * @see java.util.Collection#clear()
	 */
	public void clear() {
		elements.clear();
	}


}
