import itertools

def sort_and_count_0(alist):
    global countZero
    alist.sort()
    countZero = alist.count(0)
    return countZero , alist


def del_same(clist):
    global without_same
    without_same = [a[0] for a in itertools.groupby(sorted(clist))]
    del without_same[0]
    return without_same


def long_pok(blist, counterZero):

    result = 0
    array = sorted(set(blist))
    if counterZero != 0:
        array = array[1:]
    array_size = len(blist)
    result = 0
    for i in range(array_size):
        tmp = counterZero
        count = 1

        for j in range(i + 1, array_size):
            gap = array[j] - array[j - 1]
            case = 0

            if gap == 1:
                count += 1
            elif gap == 2 and tmp > 0:
                count += 2
                case = 1
                tmp -= 1
            elif tmp >= gap - 1 > 2 and tmp > 0:
                tmp -= gap - 1
                count += gap
                case = 2
            else:
                break

        count += tmp


        if result < count:
            result = count

    print(result)






if __name__ == '__main__':
    file = open('input_1.txt', 'r')
    list = file.readline().split()
    for i in range(list.__len__()):
        list[i] = int(list[i])
    sort_and_count_0(list)
    del_same(list)
    long_pok(without_same, countZero)
    print 'Max Length:', countZero , without_same


