//  Ball radius can no longer be adjusted with a RatingBar.
//  The initial ball radius does not correspond to the value
//  of the RatingBall.

import android.app.Activity;
import android.app.AlertDialog;
import android.util.AttributeSet;
import android.os.Bundle;
import android.graphics.*;
import android.view.*;
import android.content.* ;
import android.widget.* ;   // Class Button, etc.
import android.widget.AdapterView.AdapterContextMenuInfo ;

//import android.widget.SeekBar ;
import android.widget.SeekBar.OnSeekBarChangeListener ;

public class MovingBallSolutionsActivity extends Activity
{   
   MovingBallView moving_ball_view;
   
   SeekBar seekbar ;


    /** Called when the activity is first created. */
   @Override
   public void onCreate( Bundle savedInstanceState )
   {
      super.onCreate( savedInstanceState ) ;

      setContentView( R.layout.activity_moving_ball_solutions ) ;
        
      moving_ball_view = (MovingBallView) findViewById( R.id.moving_ball_view ) ;

      // With the following call we get a reference to the Button
      // object that has been created based on the definition in main.xml.

      Button color_button = (Button) findViewById( R.id.color_button ) ;

      // with the following line we specify that the 'floating'
      // context menu must be shown when the 'color' button is long-pressed.

      registerForContextMenu( color_button ) ;


      seekbar = (SeekBar) findViewById( R.id.seekbar_for_ball_transparency ) ;

      seekbar.setOnSeekBarChangeListener( new OnSeekBarChangeListener()
      {
         public void onProgressChanged( SeekBar seekbar,
                                         int progresValue, boolean fromUser) 
         {
            // System.out.print( "\n Changed: " + seekbar.getProgress() ) ;
     
            moving_ball_view.set_ball_alpha_value( seekbar.getProgress() ) ;
         }

         public void onStartTrackingTouch( SeekBar seekbar )
         {
            // Use a wider outline for the arrow while the SeekBar is used.
            //turning_arrow_view.set_outline_stroke_width( 8 ) ;
         }

         public void onStopTrackingTouch( SeekBar seekbar )
         {
            //turning_arrow_view.set_outline_stroke_width( 2 ) ;
         }
      });


   }

   public void reset_button_clicked( View view )
   {
      seekbar.setProgress( 128 ) ; 

      moving_ball_view.reset_ball() ;
   }

   public void left_button_clicked( View view )
   {
      moving_ball_view.move_ball_left() ;
   }
   
   public void down_button_clicked( View view )
   {
      moving_ball_view.move_ball_down() ;
   }

   public void up_button_clicked( View view )
   {
      moving_ball_view.move_ball_up() ;
   }

   public void right_button_clicked( View view )
   {
      moving_ball_view.move_ball_right() ;
   }

   // The following method is called when the Options
   // menu needs to be created. The definitions in
   // res/menu/color_selection_menu.xml are used to
   // create the menu.
    
   @Override
   public boolean onCreateOptionsMenu( Menu menu ) 
   {
       super.onCreateOptionsMenu( menu ) ;

      MenuInflater inflater = getMenuInflater() ;
      inflater.inflate( R.menu.color_selection_menu, menu ) ;

       return true;
   }

   //  The following method is used to process selections from
   //  both menus, the options menu and the context menu.

   protected boolean process_menu_selection( int menu_item_id )
   {
      switch ( menu_item_id )
      {
         case R.id.red_color_item:
            moving_ball_view.set_ball_color( Color.RED ) ;
            return true;
         case R.id.white_color_item:
            moving_ball_view.set_ball_color( Color.WHITE ) ;
            return true;
         case R.id.yellow_color_item:
            moving_ball_view.set_ball_color( Color.YELLOW ) ;
            return true;
         case R.id.green_color_item:
            moving_ball_view.set_ball_color( Color.GREEN ) ;
            return true;
         case R.id.blue_color_item:
            moving_ball_view.set_ball_color( Color.BLUE ) ;
            return true;
         case R.id.magenta_color_item:
            moving_ball_view.set_ball_color( Color.MAGENTA ) ;
            return true;
         case R.id.cyan_color_item:
            moving_ball_view.set_ball_color( Color.CYAN ) ;
            return true;
         case R.id.gray_color_item:
            moving_ball_view.set_ball_color( Color.GRAY ) ;
            return true;
         case R.id.light_gray_color_item:
            moving_ball_view.set_ball_color( Color.LTGRAY ) ;
            return true;
         case R.id.black_color_item:
            moving_ball_view.set_ball_color( Color.BLACK ) ;
            return true;
         default:
            return false ;
      }
   }

   // The following method is called when a selection in
   // the Options menu has been made.
 
   public boolean onOptionsItemSelected( MenuItem menu_item ) 
   {
      if ( process_menu_selection( menu_item.getItemId() ) )
      {
         return true ;
      }
      else
      {
         return super.onOptionsItemSelected( menu_item ) ;
      }
   }


   @Override
   public void onCreateContextMenu( ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo )
   {
      super.onCreateContextMenu( menu, v, menuInfo ) ;
      MenuInflater inflater = getMenuInflater() ;
      inflater.inflate( R.menu.color_selection_menu, menu ) ;
   }

   @Override
   public boolean onContextItemSelected( MenuItem menu_item )
   {
      if ( process_menu_selection( menu_item.getItemId() ) )
      {
         return true ;
      }
      else
      {
         return super.onContextItemSelected( menu_item ) ;
      }
   }
}

