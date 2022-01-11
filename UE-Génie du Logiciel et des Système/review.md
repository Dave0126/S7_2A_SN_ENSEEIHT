## OCL用例
```
// 详见课件第64页

// 这里的Place对应一个类
context Place

// 设置的name由大小写字母和数字组成
inv validName('Invalid name: ' + self.name):
	self.name.matches('[A-Za-z_][A-Za-z0-9_]*')

// 两个Place的name不能相同，并报错
inv deuxNomsDiffrentsDesPlaces ('This name of Place: ' + self.name + 'is been used !'):
	Place.allInstances() 
		-> select ( p | p.name = self.name)
		-> size() = 1 // 元素的个数

// implies 可以理解为 如果...就...
	(self.a.oclIsTypeOf(A) // 如果这个对象的 a 是 A类型
		implies self.b.oclIsTypeOf(B) // 那么这个对象的 b 就是 B类型
	) and (
	...
	)

```

## 