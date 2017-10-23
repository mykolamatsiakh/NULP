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


def count_length(blist,countZero):
    count = 0
    bool = True
    for left_x in blist:
        for right_x in blist[1:]:
            if right_x - left_x <=2 and right_x - left_x - 1 <= countZero and bool == True :
                count += 1
                countZero = right_x - left_x - 1
                print count , countZero
            #     co
            # if
            print count
        count = 0
        bool = True








if __name__ == '__main__':
    file = open('input_1.txt', 'r')
    list = file.readline().split()
    for i in range(list.__len__()):
        list[i] = int(list[i])
    sort_and_count_0(list)
    del_same(list)
    count_length(same,countZero)

    print('Max Length:', countZero , same)


