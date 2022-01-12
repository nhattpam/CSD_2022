
package llpkg;


/**
 * 
 * @author nhattpam
 */
public class MyLinkedList {
    LL_Element head;//reference to the beginning of the list
    LL_Element tail;//reference to the end of the list
    //Default constructor: Intitialize an empty list

    public MyLinkedList() {
    }
    public boolean isEmpty(){
        return head==null;
    }
    
    public LL_Element addFirst(LL_Element newEle){
        //Case of the list is empty
        if(head==null) head=tail=newEle;
        //Case of the list is not empty
        else{//order: newEle <-> head
            newEle.next=head;// newEle will be head
            newEle.previous=null;
            head.previous=newEle;//head is after newEle
            head=newEle;//newEle is head
        }
        return newEle;
    }
    
    public LL_Element addLast(LL_Element newEle){
        //Case of the list is empty
        if(head==null) head=tail=newEle;
        //Case of the list is not empty
        else{//order: tail <-> newEle
            newEle.next=null;// newEle will be tail
            newEle.previous=tail;
            tail.next=newEle;//tail is in front of newEle
            tail=newEle;//newEle is the tail
        }
        return newEle;
    }
    //Add new element to the position after the element p
    public LL_Element addAfter(LL_Element newEle, LL_Element p){
        if(p==null || p==tail) return addLast(newEle);
        LL_Element pAfter = p.next;
        //Order: p <-> newEle <-> pAfter
        newEle.next = pAfter;
        newEle.previous = p;
        pAfter.previous = newEle;
        p.next = newEle;
        return newEle;
    }
    
    //Add new element to the postion before the element p
    public LL_Element addBefore(LL_Element newEle, LL_Element p){
        if(p==null || p==head) return addFirst(newEle);
        LL_Element pBefore = p.previous;
        //Order: pBefore <-> newEle <-> p
        newEle.next = p;
        newEle.previous = pBefore;
        pBefore.next = newEle;
        p.previous = newEle;
        return newEle;
    }
    
    //Linear search data in the list -> Complexity is O(n)
    public LL_Element search(Comparable x){
        LL_Element t;
        for(t=head; t!=null; t=t.next)
            if(t.data.compareTo(x)==0) return t;
        return null;
    }
    
    //All remove operation have the  complexity of O(1)
    public LL_Element removeFirst(){
        //Case of the list  is empty
        if(head==null) return null;
        LL_Element result = head;
        //Case of the list has only one element
        if(head==tail) head=tail=null;
        //Case of the list has more than one element , update head
        LL_Element newHead = head.next;
        newHead.previous = null;
        head=newHead;
        return result;
    }
    
    //Remove last
    public LL_Element removeLast(){
        //Case of the list is empty
        if(tail==null) return null;
        LL_Element result = tail;
        //Case of the list has only one element
        if(tail==head) head=tail=null;
        //Case of the list has more than one element , update tail
        LL_Element newTail = tail.previous;
        newTail.next=null;
        tail=newTail;
        return result;
    }
    
    //remove a valid element in the list
    private LL_Element remove(LL_Element ele){
        if(ele==null) return null;
        //case ele is head or tail
        if(ele==head) return removeFirst();
        if(ele==tail) return removeLast();
        //Update references, order: pBefore ele pAfter
        LL_Element pBefore = ele.previous;
        LL_Element pAfter = ele.next;
        pBefore.next=pAfter;
        pAfter.previous=pBefore;
        return ele;
    }
    
    public LL_Element remove(Comparable x){
        return remove(search(x)); //==> O(n)
    }
    
    //Inner class takes a role as a tool for traversing all data in the list
    private class Traverser implements MyIterator<Comparable>{
        //Data structure of Traversor: curRef -> head -> .... -> tail
        LL_Element curRef;

        public Traverser() {
            curRef = new LL_Element(null);
            curRef.next=head;
        }
        
        @Override
        public boolean hasNext() {
            return (curRef.next!=null);
        }

        @Override
        public Comparable next() {
            curRef=curRef.next;
            return curRef.data;
        }
        
    }
    
    //The MyLinkedList class
    public MyIterator iterator(){
        if(head==null) return null;
        return new Traverser();
    }
    //Tools for add some data
    public void addFirst(Comparable... group){
        for(Comparable data: group)
            addFirst(new LL_Element(data));
    }
    public void addLast(Comparable... group){
        for (Comparable data : group) {
            addLast(new LL_Element(data));
        }
    }
       
}//End of the MyLinkedList class
