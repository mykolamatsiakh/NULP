//
//  main.cpp
//  livuiPriamokytnuk
//
//  Created by Ivan Sapiga on 17.10.2017.
//  Copyright © 2017 Ivan Sapiga. All rights reserved.
//

#include <iostream>
#include <math.h>
#include <stdio.h>

double Function(double x)
{
    return x/(3*sqrt(x*x+3));
}

double Antiderivative(double x)
{
    return x/(3*sqrt(x*x+3));
}

double CalcIntegral(double a, double b, int n)
{
    double result, h;
    int i;
    h = (b - a)  / n;
    result = 0;
    for (i = 0; i < n; i++) {
        result += Function(a + (h * i));
    }
    result *= h;
    return result;
}

double Check(double a, double b)
{
    return Antiderivative(b) - Antiderivative(a);
}

int main(int argc, char* argv[])
{
    double integral, check;
    integral = CalcIntegral(0, 1, 30);
    printf("Integral: %lf \n", integral);
    check = Check(0, 1);
    printf("Check: %lf \n", check);
    printf("Mistake: %lf \n", abs(check - integral));
    return 0;
}

