#include <math.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <assert.h>
#include <limits.h>
#include <stdbool.h>

// Computes number of inversions (i,j) with i<j and arr[j]<arr[i] of arr
long int countInversions(int *arr,int n) {
    if (n<=1) {
        return (long int)0;
    }
    long int ans =0;
    int n1 = n/2;
    int n2 = n-n1;
    int *arr2 = arr+n1;
    ans += countInversions(arr, n1);
    ans += countInversions(arr2, n2);
    int * tmp = malloc(sizeof(int)*n);
    int i = 0, j = n1,k=0;
    while(i<n1 && j<n) {
        if (arr[j]<arr[i]) {
            tmp[k++] = arr[j++];
            ans += n1-i;
        }else {
            tmp[k++] = arr[i++];
        }
    }
    while (i<n1) {
        tmp[k++] = arr[i++];
    }
    while (k>0) {
        --k;
        arr[k] = tmp[k];
    }
    free(tmp);
    return ans;
}

int main() {
    int t; 
    scanf("%i", &t);
    for(int a0 = 0; a0 < t; a0++){
        int n; 
        scanf("%i", &n);
        int *arr = malloc(sizeof(int) * n);
        for (int arr_i = 0; arr_i < n; arr_i++) {
           scanf("%i",&arr[arr_i]);
        }
        long int result = countInversions(arr,n);
        printf("%ld\n", result);
    }
    return 0;
}
