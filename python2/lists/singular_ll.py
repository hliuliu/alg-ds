

class _sll_node(object):
    def __init__(self,value,nxt = None):
        self.value = value
        self.nxt = nxt

    def __str__(self):
        return str(self.value)


class singularLL(object):
    def __init__(self, sequence= []):
        self._head = None
        self._rear = None
        self._size = 0
        sequence = iter(sequence)
        for el in sequence:
            self.insertRear(el)

    def insertHead(self,value):
        self._head = _sll_node(value, self._head)
        if self._head.nxt is None:
            self._rear -= self._head
        self._size += 1

    def insertRear(self,value):
        node = _sll_node(value)
        if self._rear is None:
            self._head = node
        else:
            self._rear.nxt = node
        self._rear = node
        self._size += 1

    def isEmpty(self):
        return self._size == 0

    def size(self):
        return self._size

    def __nonzero__(self):
        return not self.isEmpty()

    def __len__(self):
        return self.size()

    def removeHead(self):
        if self.isEmpty():
            raise Exception('List is empty')
        self._head = self._head.nxt
        if self._head is None:
            self._rear = None
        self._size -= 1

    def __iter__(self):
        curr = self._head
        while curr is not None:
            yield curr.value
            curr = curr.nxt

    def __str__(self):
        return str(list(self))

    def at(self,index):
        # not supporting negative indexing
        if not 0<=index<len(self):
            raise IndexError('Index out of bounds: {}'.format(index))
        curr = self._head
        for _ in xrange(index):
            curr =curr.nxt
        return curr.value

    def insertAt(self,index,value):
        n = len(self)
        if not 0<=index<=n:
            raise IndexError('Index out of bounds: {}'.format(index))
        if index==0:
            self.insertHead(value)
        elif index==n:
            self.insertRear(value)
        else:
            curr = self._head
            for _ in xrange(index-1):
                curr =curr.nxt
            curr.nxt = _sll_node(value, curr.nxt)
            self._size += 1

    def removeAt(self,index):
        n = len(self)
        if not 0<=index<n:
            raise IndexError('Index out of bounds: {}'.format(index))
        if index == 0:
            self.removeHead()
        else:
            curr = self._head
            for _ in xrange(index-1):
                curr =curr.nxt
            curr.nxt = curr.nxt.nxt
            if curr.nxt is None:
                self._rear = curr
            self._size -= 1

    def head(self):
        if self.isEmpty():
            raise Exception('List is empty')
        return self._head.value

    def rear(self):
        if self.isEmpty():
            raise Exception('List is empty')
        return self._rear.value
