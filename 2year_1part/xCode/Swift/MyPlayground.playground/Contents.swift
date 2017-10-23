let arr = ["FOO", "FOO", "BAR", "FOOBAR"]
var counts: [String: Int] = [:]

for item in arr {
    counts[item] = (counts[item] ?? 0) + 1
}

print(counts)  // "[BAR: 1, FOOBAR: 1, FOO: 2]"

for (value) in counts {
    print(" occurs \(value) time(s)")
}
