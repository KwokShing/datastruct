#include <iostream>
#include <vector>
#include <fstream>

using namespace std;

class Heap{
	public:
		Heap(int x):size(100),currentPos(0){array = new int[size];array[0]=-1;};
		~Heap(){delete []array;};
	private:
		int size;
		int currentPos;
		int *array;

	public:
		int FindMin();
		void DeleteMin();
		void Insert(int value);
		int GetSize();
	private:
		void Up(int i);
		void Down(int i);
		void Swap(int *a, int *b);
		void DoubleSize();
};
