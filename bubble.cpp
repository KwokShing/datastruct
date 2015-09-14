#include <iostream>
#include <vector>
#include <fstream>

using namespace std;

void swap(int *a, int *b)
{
	int c;
	c = *b;
	*b = *a;
	*a = c;
}

void bubble(int *arr, int len)
{
	int newlen = len;
	bool flag = true;
	while(newlen){
		flag = false;
		newlen = 0;
		for(int i = 1; i < len; ++i){
			if(arr[i-1]>arr[i]){
				swap(&arr[i-1],&arr[i]);
				flag = true;
				newlen = i;
			}
			for(int j = 0; j < 10; ++j)
				cout<<arr[j]<<" ";
			cout<<endl;
		}
	}
}


/*
void bubble(int *arr, int len)
{
	cout<<sizeof(arr)<<" "<<sizeof(arr[0])<<endl;
	cout<<"len: "<<len<<endl;
	int newlen = len;
	bool flag = true;
		flag = false;
		for(int i = 0; i < len; ++i){
			for(int j = i; j < len; ++j){
				if(arr[j]<arr[i]){
					swap(&arr[j],&arr[i]);
					flag = true;
					newlen = i;
				}
				for(int k = 0; k < 10; ++k)
					cout<<arr[k]<<" ";
				cout<<endl;
			}
		}
}
*/

void print(int *arr)
{
	for(int i = 0; i < 10; ++i)
		cout<<arr[i]<<" ";
	cout<<endl;
}
int main()
{
	int a = 1;
	int b = 2;
	cout<<a<<" "<<&a<<" "<<b<<" "<<&b<<endl;
	swap(&a,&b);
	cout<<a<<" "<<&a<<" "<<b<<" "<<&b<<endl;
	ifstream in("input");
	if(!in)
		return 0;
	string s;
	int d;
	int count = 0;
	int arr[10];
	while(in>>d){
		//cout<<d<<endl;
		arr[count++] = d;
	}
	cout<<"------before------"<<endl;
	print(arr);
	bubble(arr,10);
	cout<<"------after-------"<<endl;
	print(arr);
	in.close();
	return 0;
}
