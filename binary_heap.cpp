#include "binary_heap.h"

int Heap::FindMin()
{
	if(0 != GetSize())
		return array[1];
	else
		return -1;
}

void Heap::DeleteMin()
{
	if(0 != GetSize()){
		Swap(&array[1],&array[currentPos]);
		currentPos--;
		Down(1);
	}
}

void Heap::Insert(int value)
{
	if(currentPos+1 <= size){
		int i = currentPos+1;
		array[i] = value;
		currentPos++;
		Up(i);
	}
	//cout<<"heap: "<<array[0]<<endl;
		//DoubleSize();
}

void Heap::Up(int i)
{
	if(i>1){
		int parent = i/2;
		cout<<"parent: "<<array[parent]<<" cur: "<<array[i]<<endl;
		if(array[parent]>array[i]){
			Swap(&array[i],&array[parent]);
			Up(i);
		}
	}
}

void Heap::Down(int i)
{
	int leftIdx = 2*i;
	int rightIdx = 2*i+1;
	int curSize = GetSize();
	if(leftIdx > curSize)
		return;
	else if(leftIdx == curSize){
		Swap(&array[i],&array[leftIdx]);
		Down(leftIdx);
	}
	else{
		if(array[leftIdx] < array[rightIdx]){
			Swap(&array[i],&array[leftIdx]);
			Down(leftIdx);
		}
		else{
			Swap(&array[i],&array[rightIdx]);
			Down(rightIdx);
		}
	}
}

void Heap::Swap(int *a, int *b)
{
	int c = *a;
	*a = *b;
	*b = c;
}

void Heap::DoubleSize()
{

}

int Heap::GetSize()
{
	return currentPos;
}

int main()
{
	Heap h = Heap(10);
	ifstream inFile("input");
	if(!inFile){
		return 0;
	}
	int d;
	while(inFile>>d){
		h.Insert(d);
	}
	cout<<h.FindMin()<<endl;
	cout<<"----------------"<<endl;
	h.DeleteMin();
	cout<<h.FindMin()<<endl;
	cout<<"----------------"<<endl;
	h.DeleteMin();
	cout<<h.FindMin()<<endl;
	inFile.close(); 
	return 0;
}
