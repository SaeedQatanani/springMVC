package junitsinglylinkedlist;

public class SLL {
	public Node head;
	public SLL() {
		this.head = null;
	}
    public void add(int value) {
        Node newNode = new Node(value);
        if(head == null) {
            head = newNode;
        } else {
            Node runner = head;
            while(runner.next != null) {
                runner = runner.next;
            }
            runner.next = newNode;
        }
    }  
	public void removeFront() {
		head = head.next;
	}
}
