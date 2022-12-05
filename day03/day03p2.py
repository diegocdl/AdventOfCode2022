def get_value(rucksack_item):
    if ord(rucksack_item) >= ord('a') and ord(rucksack_item) <= ord('z'):
        return ord(rucksack_item) - ord('a') + 1
    elif ord(rucksack_item) >= ord('A') and ord(rucksack_item) <= ord('Z'):
        return ord(rucksack_item) - ord('A') + 27
    else:
        return 0

def get_shared_items(rucksack1, rucksack2):
    shared_items = set()
    for item in rucksack1:
        if item in rucksack2:
            shared_items.add(item)
    return list(shared_items)

def main():
    priority_sum = 0
    with open('input.txt') as input_file:
        rucksacks = input_file.read().split()
        for i in range(0, len(rucksacks), 3):
            for item in rucksacks[i]:
                if (item in rucksacks[i + 1]) and (item in rucksacks[i + 2]):
                    priority_sum += get_value(item)
                    break
        
        print('Result:', priority_sum)

if __name__ == '__main__':
    main()