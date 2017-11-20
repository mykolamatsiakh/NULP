pattern = ["a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n",
               "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"]


def add_arr(blist):
    count = 0
    n = 0
    k = 0

    for element_x in blist:
        new_list = blist[n] + element_x
        new_list = set(new_list)
        length_of_element = len(new_list)
        my_pattern = pattern[:length_of_element]
        if new_list.issubset(my_pattern) and len(new_list) <= 29:
            count = count + 1
            print "ok: ", new_list
            del blist[k]
            del blist[n]
            break
        else:
            print "neOK: ", new_list
    n = n+1
    k = k+1
    print count

if __name__ == '__main__':
    list = open('input_1.txt', 'r').readlines()
    for i in range(list.__len__()):
        list[i] = str(list[i].strip('\n'))
    add_arr(list)