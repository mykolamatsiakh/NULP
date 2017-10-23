//
//  main.cpp
//  pawa
//
//  Created by Ivan Sapiga on 06.10.17.
//  Copyright © 2017 Ivan Sapiga. All rights reserved.
//

#include <iostream>

#include <cstdlib>
#include <iostream>

using namespace std;
int main()
{
    int n=4;
    double A[4][4]={{8.3,3.04,4.1,1.9},{3.92,8.45,7.36,2.46},{3.77,7.63,8.04,2.28},{2.21,3.23,1.69,6.59}};
        double V[4][4],C[4][4], det=1;
        for (int i=0; i<n; i++)
            for (int j=0; j<n;j++)
                V[i][j]=A[i][j];
        for (int k=0; k<n ; k++)
        {
            det*=V[k][k];
            for (int i=k+1;i<n;i++)
                for (int j=k+1;j<n;j++)
                {
                    C[k][j]=V[k][j]/V[k][k];
                    V[i][j]+=-V[i][k]*C[k][j];
                    
                }
            
            
        }
    cout<< det;
    cout<< endl;
        return 0;
    }
