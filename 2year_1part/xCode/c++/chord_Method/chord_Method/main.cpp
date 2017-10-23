#include <iostream>
#include <cmath>
#include <locale.h>
using namespace std;
double f(double x)
{
    return pow(x,3)+pow(x,2)+3;
}

double findRoot(double a, double b, double e)
{
    while(fabs(f(b)) > e)
    {
        a = b - ((b - a) * f(b))/(f(b) - f(a));
        b = a - ((a - b) * f(a))/(f(a) - f(b));
    }
    return b;
}
int main()
{
    setlocale(LC_ALL,"Russian");
    double a, b, e;
    a=-3; b=-1; e=0.0001;
    cout<<"Корень рівняння x="<<findRoot(a, b, e)<<endl;
}
