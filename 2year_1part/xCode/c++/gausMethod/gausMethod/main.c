


#include <stdlib.h>
#include <stdio.h>
#include <math.h>
#include <math.h>
#include <conio.h>
#include <iostream>






int _tmain(int argc, _TCHAR* argv[])
{

    int n=4, h, w, z;
    double A[4][4] = {{8.3,2.82,4.1,1.9},
        {3.92, 8.45,7.62,2.46},
        {3.77,7.37,8.04,2.28},
        {2.21,3.49,1.69,6.69}};
    
    double V[4][4],C[4][4], det=1;
    double max,value;
    for (int i=0; i<n;i++)
        for (int j=0; j<n;j++)
            V[i][j]=A[i][j];
    for (int k=0; k<n;k++)
    {
        //сортування
        max=fabs(V[k][k]); h=k; w=k;
        for (int l=k; l<n; l++)
            for (int f=k; f<n; f++)
                if (max<fabs(V[l][f]))
                {
                    max=fabs(V[l][f]);
                    h=1; w=f;
                }
        for (int d=0; d<n; d++)
        {
            value=V[k][d]; V[k][d]=V[h][d]; V[h][d]=value;
        }
        //сортування по рядку
        for (int d=0;d<n;d++)
        {
            if(d<k)
            {value=C[d][k]; C[d][k]=C[d][w]; C[d][w]=value; }
            else
            {
                value=V[d][k]; V[d][k]=V[d][w]; V[d][w]=value; }
        }
        //кінець сортування
        det*=V[k][k]*pow(-1.0,w+h);
        for (int i=k+1; i<n; i++)
            for (int j=k+1;j<n; j++)
            {
                C[k][j]=V[k][j]/V[k][k];
                V[i][j]+= -V[i][k]*C[k][j];
                
            }
    }
    cout<<"det: ";
    cout<<fabs(det);
    _getch();
    return 0;
}
