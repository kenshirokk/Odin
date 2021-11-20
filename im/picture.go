package main

type Picture struct {
	Id       int
	RootName string `gorm:"column:root_name"`
	Name     string
	Number   int
	Deleted  bool
	Path     string
	Pa       string
}

func (Picture) TableName() string {
	return "picture"
}
