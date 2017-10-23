//
//  main.cpp
//  Insert
//
//  Created by Ivan Sapiga on 21.10.2017.
//  Copyright © 2017 Ivan Sapiga. All rights reserved.
//

#include <iostream>
#include <cstdlib>
#include <iomanip>

using namespace std;

void Sort(int* arr, size_t size)
{
    for (size_t i = 0; i < size; i++)
    {
        for (size_t j = 0; j < size - 1; j++)
        {
            for (size_t k = j + 1; k < size; k++)
            {
                if (arr[i][j] < arr[i][k] )
                    swap(arr[i][j],arr[i][k]);
            }
        }
    }
}

void init(int** arr, size_t rows, size_t columns)
{
    for (size_t i = 0; i < rows; i++)
    {
        arr[i] = new int[columns];
    }
    for (size_t i = 0; i < rows; i++)
    {
        for (size_t j = 0; j < columns; j++)
        {
            arr[i][j] = rand() % 20 - 10;
        }
    }
}

void show(int** arr, size_t rows, size_t columns)
{
    cout << "Matrix:" << endl;
    for (size_t i = 0; i < rows; i++)
    {
        for (size_t j = 0; j < columns; j++)
        {
            cout << setw(3) << arr[i][j] << ' ';
        }
        cout << endl;
    }
}

void delmem(int** arr, size_t rows, size_t columns)
{
    for (size_t i = 0; i < rows; i++)
    {
        delete[] arr[i];
    }
    delete[] arr;
}

int main()
{
    setlocale(0, "");
    size_t n, m; cin >> n >> m;
    int** arr = new int*[n];
    init(arr, n, m);
    show(arr, n, m);
    int* temp = new int[n*m];
    size_t k = 0;
    for (size_t i = 0; i < n; i++)
    {
        for (size_t j = 0; j < m; j++)
        {
            temp[k] = arr[i][j];
        }
        
    }
    
}
