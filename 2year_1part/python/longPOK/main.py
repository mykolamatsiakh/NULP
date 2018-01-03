
def searcher(sorted_array):
    jokers = sum((1 for el in sorted_array if el == 0))
    sorted_array = sorted_array[jokers:]
    results = list()
    max_i = len(sorted_array) - 1
    j = 0
    jokers_left = jokers
    while j < max_i:
        i = j
        count = 1
        jokers_i = jokers
        jokers_left = jokers
        while i <= max_i - 1:
            if sorted_array[i] + 1 != sorted_array[i + 1]:
                if not jokers_i:
                    break
                if sorted_array[i] == sorted_array[i + 1]:
                    jokers_left -= 1
                jokers_i -= 1
            i += 1
            count += 1
        else:
            j += 1
        j = i + 1
        results.append(count)
    count = 0
    if results:
        count = max(results)
    return count + jokers_left


def merge_sort(lst):
    if not lst:
        return []
    lists = [[x] for x in lst]
    while len(lists) > 1:
        lists = merge_lists(lists)
    return lists[0]


def merge_lists(lists):
    result = []
    for i in range(0, len(lists) // 2):
        result.append(merge2(lists[i * 2], lists[i * 2 + 1]))
    if len(lists) % 2:
        result.append(lists[-1])
    return result


def merge2(xs, ys):
    i = 0
    j = 0
    result = []
    while i < len(xs) and j < len(ys):
        x = xs[i]
        y = ys[j]
        if x > y:
            result.append(y)
            j += 1
        else:
            result.append(x)
            i += 1
    result.extend(xs[i:])
    result.extend(ys[j:])
    return result


def read_value(path):
    with open(path) as f:
        data = f.readline()
    ar = [int(i) for i in data.split()]
    return ar


def write_value(path, result):
    with open(path, 'w') as f:
        f.write(str(result))


def enhanced_searcher(array, reverse=False):
    jokers = sum((1 for el in array if el == 0))
    array.sort() if not reverse else array.sort(reverse=True)
    array = array[jokers:] if not reverse else array[:-jokers]
    results = [jokers]
    if not array:
        return jokers
    if len(array) == 1:
        return jokers + 1
    ascending = -2 if reverse else 0
    equal = 1 if reverse else -1
    j = 0
    max_len = len(array) - 1
    while j < max_len:
        i = j
        count = 1
        jokers_left = jokers
        while i < max_len:
            diff = array[i + 1] - array[i] - 1
            if diff == ascending:
                count += 1
            elif diff == equal:
                i += 1
                # count += jokers_left
                # jokers_left = 0
                # break
                continue
            elif diff > jokers_left:
                count += jokers_left
                jokers_left = 0
                break
            else:
                jokers_left -= diff
                count += diff + 1
            i += 1
        if diff > jokers:   # there is no bug since array here is at least 2 elements long
            j = i + 1
        else:
            j += 1
        if jokers_left:
            count += jokers_left
        results.append(count)
    return max(results)


def silly_searcher(array):
    jokers = sum((1 for el in array if el == 0))
    array.sort()
    array = array[jokers:]
    results = [jokers]
    if not array:
        return jokers
    if len(array) == 1:
        return jokers + 1
    j = 0
    max_len = len(array) - 1
    while j < max_len:
        i = j
        count = 1
        jokers_left = jokers
        while i < max_len:
            diff = array[i + 1] - array[i] - 1
            if diff == 0:   # magic number 1
                count += 1
            elif diff == -1: # magic number 2
                i += 1
                continue
            elif diff > jokers_left:
                count += jokers_left
                jokers_left = 0
                break
            else:
                jokers_left -= diff
                count += diff + 1
            i += 1
        j += 1
        if jokers_left:
            count += jokers_left
        results.append(count)
    return max(results)

if __name__ == '__main__':
    arr = read_value('input_1.txt')
    res_1 = enhanced_searcher(arr)
    # res_2 = silly_searcher(arr)
    write_value('lngpok.out', res_1)
    print res_1
