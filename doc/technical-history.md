## Tech History

### Site Scraping

I went with Akka and Jsoup. Akka for no reason in particular, it's useful for my day job and I use it wherever I can in
the hopes that I learn more about it passively. I have used Jsoup in the past and I have absolutely no complaints, 
it seems to be the de-facto jvm solution for parsing HTML and I can definitely see why. 

### Deserializing Json into Scala Case Classes

This was an unexpected PITA. There seems to be no perfect solution, with "perfect" being a json deserialization
solution that can handle case classes easily while obeying parameters with defaults. I'd love to use play-json but
the model I get from POE has many more parameters than 22, which causes play json to freak out. 

I initially saw some posts on SO talking about using a library called Slickless that does something with lists to work 
around the problem but I avoided it because I didn't understand it. After trying straight up Jackson and some twitter 
thing called Finatra and being unsatisfied with both (Jackson doesn't know about scala defaults so it will actually set 
things to null, I never got Finatra to do anything other than mimic the core Jackson behavior and there's no 
documentation) I'm gonna try out Slickless.

Just kidding I'm not gonna try it yet, there is a play-json extensions library that I'm going to try first. The extensions
seem to be working except for one issue; an Item in POE references itself, as an Item can have socketed items. This confuses
the json library as it encounters the item type when trying to build the format for the item type, and it hasn't 
finished building it yet. Maybe we can leave that field as a linked list or whatever it naturally deserializes to, then
format it later? The recursion issue might have something to do with the format being implicit.

Instead of using jackson annotations to get the oddly formatted json into a format that follows scala conventions, I 
think I'm just going to write functions that basically "wrap" the poorly named fields with better names. That
will be a lot faster and less work than trying to get play json macros to obey jackson annotations, which probably 
isn't even possible.

### Persistence

At the moment, the only way this project gets data is through scraping other sites for information. I'd like to be able
to persist that data somehow and only update my local copy every once in a while whenever it's necessary. In my wildest
dreams I'd get a "base" set of information on POE mods, then update those values with my own data calculated from the 
POE trade api. I have to think of a way to:

1. Uniquely identify mods in a repeatable way, so that when we encounter one through the trade api or scraping a mod 
   site, we can create an identifier for a mod in an idempotent way. Perhaps some sort of hash on the filled out case
   class.
   
2. Stream new pieces of information into a repo of some kind and "compile" it into a new set of aggregated information.
   This might be a reasonable use case to explore event sourcing especially since it can be scoped to a very specific 
   portion of this project.
   

I'm coming back to this project after not touching it for a week or two. It's funny how drastically my perspective can 
change with the release of synthesis around the corner. I look at all the stuff I wrote above and think "that's garbage,
just get it working". Thus I'm going to store everything in postgres and use slick for my db access library.
