import operator as _op


class _btree_node(object):
    def __init__(self,compare = _op.lt):
        # comapare(a,b) True iff "a is less than b"
        self.n = 0
        self.leaf = True
        self.keys = []
        self.children = None
        self.compare = compare

    def setLeaf(self, isLeaf):
        if self.leaf != isLeaf:
            self.leaf = isLeaf
            if isLeaf:
                self.children = None
            else:
                self.children = [None]*(self.n+1)

    def _search(self, key):
        i =0
        while i<self.n and self.compare(self.keys[i],key):
            i+=1
        if i<self.n and key == self.keys[i]:
            return self, i
        if self.leaf:
            return None
        return self.children[i]._search(key)

class Btree(object):
    def __init__(self, degree=2, sequence=[], compare = _op.lt):
        # comapare(a,b) True iff "a is less than b"
        self._root = _btree_node(compare)
        self._size = 0
        self._degree =_degree
        self.compare = compare
        # insert sequence



    def __contains__(self, key):
        return self._root._search(key) is not None

    def insertKey(self, key):
        r = self._root
        t = self._degree
        if r.n == (t<<1)-1:
            s = _btree_node(self.compare)
            self._root = s
            s.setLeaf(False)
            s.children[0] = r
            self._split_child(s,0)
            r = s
        self._insert_nonfull(r,key)
        self._size += 1

    def _split_child(self,node,index):
        x = node
        z = _btree_node(self.compare)
        y = x.children[index]
        t = self._order
        z.n = t-1
        z.setLeaf(y.leaf)
        z.keys = []
        for j in xrange(t-1):
            z.keys.append(y.keys[j+t])
        if not y.leaf:
            for j in xrange(t):
                z.children[j] = y.children[j+t]
        y.n = t-1
        x.children.append(None)
        for j in xrange(x.n,index,-1):
            x.children[j+1] = x.children[j]
        x.children[index+1]= z
        for j in xrange(x.n-1,index-1,-1):
            x.keys[j-1] = x.keys[j]
        x.keys[index] = y.keys[index]
        x.n += 1

    def _insert_nonfull(self,node,key):
        i = node.n-1
        t = self._order
        if node.leaf:
            while i>=0 and self.compare(key,node.keys[i]):
                if i+1 == node.n:
                    node.keys.append(None)
                node.keys[i+1] =  node.keys[i]
                i-=1
            if i+1 == node.n:
                node.keys.append(None)
            node.keys[i+1] = key
            node.n += 1
        else:
            while i>=0 and self.compare(key,node.keys[i]):
                i -= 1
            i += 1
            if node.children[i].n == (t<<1)-1:
                self._split_child(node,i)
                if self.compare(node.keys[i],key):
                    i += 1
            self._insert_nonfull(node.children[i], key)

    def __len__(self):
        return self._size
