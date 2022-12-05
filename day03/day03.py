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
    with open('input.txt') as input_file:
        priority_sum = 0
        for line in input_file:
            rucksack = line[:-1]
            rucksack1 = rucksack[:len(rucksack)//2]
            rucksack2 = rucksack[len(rucksack)//2:]
            
            shared_items = get_shared_items(rucksack1, rucksack2)
            priority_sum += get_value(shared_items[0])        
        print('Result:', priority_sum)

if __name__ == '__main__':
    main()