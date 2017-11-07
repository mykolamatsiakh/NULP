import itertools

def sort_and_count_0(alist):
    global countZero
    alist.sort()
    countZero = alist.count(0)
    return countZero , alist


def del_same(clist):
    global same
    same = [a[0] for a in itertools.groupby(sorted(clist))]
    del same[0]
    return same


def long_pok(blist, counterZero):
    combination = 1
    for i in range(len(blist) - 1):
        if ((blist[i+1]-blist[i]) == 1):
            combination += 1
        elif ((blist[i+1] - blist[i]) <= counterZero):
            m = blist[i + 1] - blist[i]
            counterZero -= m - 1
            combination += m-1
            if ((blist[i] - blist[i - 1]) != 1):
                continue
            elif ((blist[i + 1] - blist[i]) == 1):
                combination += 1
        elif ((blist[i+1] - blist[i]) > counterZero):
            combination += counterZero
            counterZero = 0
            if ((blist[i] - blist[i - 1]) != 1):
                continue
            elif ((blist[i + 1] - blist[i]) == 1):
                combination += 1
    combination += counterZero
    print(combination)
    del blist[0]
    return combination







if __name__ == '__main__':
    file = open('input_1.txt', 'r')
    list = file.readline().split()
    for i in range(list.__len__()):
        list[i] = int(list[i])
    sort_and_count_0(list)
    del_same(list)
    long_pok(same, countZero)
    print 'Max Length:', countZero , same


