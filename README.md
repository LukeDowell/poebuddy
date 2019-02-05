# PoeBuddy

The goal of this project is to create a web application that can accurately
provide players with the best way to craft any given item in PoE. 

The definition of "best" is variable. For some it might mean "fastest", and for
others it might mean "cheapest". The application should be able to take this into
account and provide several solutions to a given crafting problem.

### [AffixScraper][AffixScraperDoc]

Scrapes mod information from poedb.tw and persists it

### [Common][CommonDoc]

Contains models and libraries relevant to the rest of the project

### [El TradeAPI Escuchador][EscuchadorDoc]

Listens to the TradeAPI. 

### Web

Play + React application that serves the site and REST api.

### [Technical History][TechnicalHistoryDoc]

A log of technical choices I made and why. Probably not useful, mostly just a garbage can of 
ideas that I can refer to later if I need to.

[AffixScraperDoc]: affixscraper/
[CommonDoc]: common/
[EscuchadorDoc]: el-tradeapi-escuchador/
[TechnicalHistoryDoc]: doc/technical-history.md
