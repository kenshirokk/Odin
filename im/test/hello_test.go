package main

import (
	"testing"
)

func TestHello(t *testing.T) {

	assertCorrectmessage := func(t *testing.T, got, want string) {
		t.Helper()
		if got != want {
			t.Errorf("got %q want %q", got, want)

		}
	}

	t.Run("say hello to people", func(t *testing.T) {
		got := Hello("kenshiro")
		want := "Hello kenshiro"
		assertCorrectmessage(t, got, want)
	})

	t.Run("say hello to nobody", func(t *testing.T) {
		got := Hello("")
		want := "Hello World"
		assertCorrectmessage(t, got, want)
	})

}
