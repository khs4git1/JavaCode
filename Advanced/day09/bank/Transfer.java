package day09.bank;

public interface Transfer {
	boolean isMemeber(String email); //S
	boolean checkBalance(String sender, long amount); //S
	boolean minus(String sender, long amount); //U
	boolean plus(String receiver, long amount); //U
	boolean log(String sender, String receiver, long amount); //I
	void showResult(String sender, String receiver); //S
	void closeAll();
	
	boolean transfer(String sender, String receiver, long amount); // for User
}

//1. �������� �̸����� �´��� üŷ 
//2. ������ ����� �ܾ��� ��ü�ݾ׺��� ������ üŷ 
//3. ������ ����� �ܾ��� - 
//4. �޴� ����� �ܾ��� + 
//5. ��ü ��� 
//6. ���� ������ ������ 
//7. ���ᰴü�� �ݱ� 

//8. ��ü�ϱ� �޼ҵ� for User  