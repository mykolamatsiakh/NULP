
comparationCounter = 0
swapCounter = 0
def insertion_sort(alist):
    k = 0
    global result1
    n = len(alist) - 1
    comp = 0
    swap = 0
    while k+1 <= n:
        i = k+1
        curr_val = alist[i]
        comp += 1
        while i > 0 and alist[i-1] > curr_val:
            alist[i] = alist[i - 1]
            i = i-1
            swap += 1
            comp += 1
        alist[i] = curr_val
        k += 1
    result1 = (comp, swap)
    return alist, result1


if __name__ == '__main__':
    file = open('1000_input.txt', 'r')
    list = file.readline().split()
    for i in range(list.__len__()):
        list[i] = float(list[i])
    insertion_sort(list)
    insertion_result = (comparationCounter, swapCounter)
    print('Insertion sort for 1000:  compares:  {}, swap:  {}'.format(*result1))