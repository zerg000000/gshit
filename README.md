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

      font( name: 'header', bold: true, fontHeight: 24, underline: 'single' )
      font( name: 'normal', fontHeight: 12)

      style( name: 'header', font: 'header' )
      style( name: 'normal', font: 'normal' )
      style( name: 'normal-date', font: 'normal', format: 'date')
      style( name: 'normal-money', font: 'normal', format: 'money', alignment: 'right')

      css( sheet: 'Daily summary', row: 0, col: 1, style: 'normal-date' )
      css( sheet: 'Daily summary', row: 1, col: 0..5, style: 'header' )
      css( marker: 'transaction', sheet: 'Daily summary', row: 0, col: 0..4, style: 'normal' )
      css( marker: 'transaction', sheet: 'Daily summary', row: 0, col: 1, style: 'normal-date' )
      css( marker: 'transaction', sheet: 'Daily summary', row: 0, col: 4, style: 'normal-money' )

    }.write(new FileOutputStream('~/daily.summary.xlsx'))