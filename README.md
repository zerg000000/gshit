GShit
=====

Make your excel report more expressive! apache-poi provides a
rich set of excel api, but it is painful to use in any project.
GShit is just a small DSL build on top of apache-poi to fulfill my need
 of more visable excel report design.

[![Build Status](https://secure.travis-ci.org/zerg000000/gshit.png)](http://travis-ci.org/zerg000000/gshit)

Getting Started
---------------

Building a standard excel report is a piece of cake!
```groovy
new ExcelBuilder()
.workbook {
  sheet('cool') {
    row { cell('This', 'is', 'easy', '.') }
  }
}.write('~/cake.xlsx')
```
That's damn easy to create a complex report.
Especially, you can use any groovy magic in the script.
```groovy
new ExcelBuilder()
.workbook {
  sheet('long sheet') {
    columns('auto', 5 * 256, 'auto', 'auto', 'auto') // this is the column widths

    1..100.each { i ->
      row { cell('No.', i, 'student', 'from day', new Date()) }
    }
  }
}.write('~/loop.xlsx')
```
Defining style elements is much more simplified.
```groovy
format( 'date', 'yyyy-MMM-dd' )
format( 'money', '#,##0.00' )

font( id: 'header', bold: true, fontHeight: 24, underline: 'single')
font( id: 'normal', fontHeight: 12)

style( id: 'header', font: 'header' )
style( id: 'normal', font: 'normal' )
style( id: 'normal-date', font: 'normal', format: 'date')
style( id: 'normal-money', font: 'normal', format: 'money', alignment: 'right')
```
Styling with extremely flexible syntax
```groovy
new ExcelBuilder('xls')
.workbook {
  sheet('pretty shit') {
     row {
       cell('abc','abc','abc') // these cells will be styled with 'gg'
     }
  }

  style(id: 'gg', alignment: 'right')

  css {
    cell(style: 'gg', row: 0, col: 0..2)
  }
}.write('~/styled.xls')
```

An complete example
```groovy
new ExcelBuilder()
.workbook {
  sheet( 'Daily summary' ) {
    columns( 7 * 256, 52 * 256, 'auto', 'auto', 'auto' )
    row { cell( 'Printed', new Date() ) }
    row { cell( 'No.', 'DateTime', 'From', 'To', 'Amount' ) }
    transactions.eachWithIndex { tran, i ->
      marker( 'transaction' ) {
        row { cell( i, tran.time, tran.from, tran.to, tran.amount ) }
      }
    }
  }

  format( 'date', 'yyyy-MMM-dd' )
  format( 'money', '#,##0.00' )

  font( id: 'header', bold: true, fontHeight: 24, underline: 'single' )
  font( id: 'normal', fontHeight: 12)

  style( id: 'header', font: 'header' )
  style( id: 'normal', font: 'normal' )
  style( id: 'normal-date', font: 'normal', format: 'date')
  style( id: 'normal-money', font: 'normal', format: 'money', alignment: 'right')

  css{
    style('normal') {
      sheet('Daily summary') {
        cell(style: 'normal-date', row: 0, col: 1 )
        marker('transaction') {
          cell(row: 0, col: 0..4 )
          cell(style: 'header', row: 1, col: 0..5)
          cell(style: 'normal-money', row: 0, col: 4 )
          cell(style: 'normal-date', row: 0, col: 1 )
        }
      }
    }
  }
}.write(new FileOutputStream('~/daily.summary.xlsx'))
```