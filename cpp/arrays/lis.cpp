// C++ implementation to find longest increasing subsequence
// in O(n Log n) time.
#include <bits/stdc++.h>
#include <iostream>
using namespace std;

#define MAX_SIZE (1<<15)

// #define STRICT

int binsearch(int key, int tail[], int arr[], int start, int end) {
    while (end-start>2) {
        int mid = (start+end)/2;
        if (
#ifndef STRICT
            arr[tail[mid]]>key
#else
            arr[tail[mid]]>=key
#endif
            ) {
            end = mid+1;
        }else {
            start=mid+1;
        }
    }
    for (int i = start; i<end ;i++) {
        if (
#ifndef STRICT
            arr[tail[i]]>key
#else
            arr[tail[i]]>=key
#endif
        ) {
            return i;
        }
    }
    return end; // should not execute
}


void LIS (int arr[],int n, int lis[],int &lis_sz) {
    int tail[MAX_SIZE], prev[MAX_SIZE];
    for (int i =0;i<n;i++) {
        tail[i] =0;
        prev[i] = -1;
    }
    
    lis_sz = 1;
    for (int i =1;i<n; i++) {
        if (
// #ifndef STRICT
//             arr[i]<=arr[tail[0]]
// #else
            arr[i]<arr[tail[0]]
// #endif
        ) {
            tail[0] = i;
        }else if (
#ifndef STRICT
            arr[i]>=arr[tail[lis_sz-1]]
#else
            arr[i]>arr[tail[lis_sz-1]]
#endif
        ) {
            prev[i] = tail[lis_sz-1];
            tail[lis_sz++] = i;
        }else {
            int j = binsearch(arr[i], tail, arr, 1, lis_sz);
            prev[i] = tail[j-1];
            tail[j] = i;
        }
    }
    
    int m = lis_sz;
    int k = tail[lis_sz-1];
    while (k>=0) {
        lis[--m] = arr[k];
        k = prev[k];
    }
}




int main()
{
	int arr[] = { 2, 5, 5, 3, 7, 11, 8, 10, 13, 6 };
	int n = sizeof(arr)/sizeof(arr[0]);
	
	int lis[MAX_SIZE];
	
	int lis_sz;

	LIS(arr, n, lis, lis_sz);
	
	cout << "size of LIS: " << lis_sz << endl;
	
	for (int i =0; i<lis_sz;i++) {
	    cout << lis[i] << " ";
	}
	
	cout << endl;

	return 0;
}
