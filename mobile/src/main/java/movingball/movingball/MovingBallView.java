import android.graphics.* ;
import android.view.* ;
import android.content.Context ;
import android.util.AttributeSet ;

public class MovingBallView extends View
{
   int initial_center_point_x, initial_center_point_y ;

	int ball_center_point_x = 100 ;
   int ball_center_point_y = 100 ;
   
   int ball_color  =  Color.RED ;
   int ball_alpha_value = 128 ;
   int initial_ball_alpha_value = 128 ;

   int view_width, view_height ;

   float ball_radius = 50 ;

   float initial_ball_radius ;

   public MovingBallView( Context context ) 
   {
      super( context ) ;
   }
    
   //  The following constructor is needed when MovingBallView object is
   //  specified in an XML file, and is thus created automatically.

   public MovingBallView( Context context, AttributeSet attributes ) 
   {
      super( context, attributes ) ;
   }

   public void onSizeChanged( int current_width_of_this_view,
                              int current_height_of_this_view,
                              int old_width_of_this_view,
                              int old_height_of_this_view )
   {
      ball_center_point_x  = current_width_of_this_view / 2 ;
      ball_center_point_y  = current_height_of_this_view  / 2 ;

      view_width = current_width_of_this_view ;
      view_height = current_height_of_this_view ;

      initial_center_point_x = ball_center_point_x ;
      initial_center_point_y = ball_center_point_y ;

      ball_radius = view_width / 8 ;

      initial_ball_radius = ball_radius ;
   }

   public void reset_ball() 
   {
      ball_center_point_x = initial_center_point_x ;
      ball_center_point_y = initial_center_point_y ;

      ball_color = Color.RED ;

      ball_radius = initial_ball_radius ;
      ball_alpha_value = initial_ball_alpha_value ;

      invalidate() ;
   }

   public void set_ball_shrinkage( float given_shrinkage )
   {
      // given_shrinkage should be between 0 ... 1

      ball_radius = ( view_width / 2 ) * given_shrinkage ;
      invalidate() ;
   }

   public void set_ball_alpha_value( int given_alpha_value )
   {
      ball_alpha_value = given_alpha_value ;
      invalidate() ;
   }


   public void move_ball_left() 
   {
      ball_center_point_x -= 3 ;

      if ( ball_center_point_x - ball_radius < 0 )
      {
         ball_center_point_x = initial_center_point_x ;
         ball_center_point_y = initial_center_point_y ;
      }

      invalidate() ;
   }

   public void move_ball_down() 
   {
      ball_center_point_y += 3 ;

      if ( ball_center_point_y + ball_radius > view_height )
      {
         ball_center_point_x = initial_center_point_x ;
         ball_center_point_y = initial_center_point_y ;
      }

      invalidate() ;
   }

   public void move_ball_up() 
   {
      ball_center_point_y -= 3 ;

      if ( ball_center_point_y - ball_radius < 0 )
      {
         ball_center_point_x = initial_center_point_x ;
         ball_center_point_y = initial_center_point_y ;
      }

      invalidate() ;
   }

   public void move_ball_right() 
   {
      ball_center_point_x += 3 ;

      if ( ball_center_point_x + ball_radius > view_width )
      {
         ball_center_point_x = initial_center_point_x ;
         ball_center_point_y = initial_center_point_y ;
      }

      invalidate() ;
   }

   public void set_ball_color( int new_color )
   {
      ball_color = new_color ;
      invalidate() ;
   }
    
   @Override
   protected void onDraw( Canvas canvas ) 
   {
      Paint background_paint = new Paint() ;
      background_paint.setColor( Color.WHITE ) ;
      
      canvas.drawPaint( background_paint ) ; // This fills the entire canvas.

      Paint filling_paint = new Paint() ;
      filling_paint.setStyle( Paint.Style.FILL ) ;
      filling_paint.setColor( ball_color ) ;
      filling_paint.setAlpha( ball_alpha_value ) ;

    	canvas.drawCircle( ball_center_point_x,
                         ball_center_point_y, ball_radius, filling_paint ) ;

      Paint outline_paint = new Paint() ;
      outline_paint.setStyle( Paint.Style.STROKE ) ;
      // Default color for a Paint is black.

    	canvas.drawCircle( ball_center_point_x,
                         ball_center_point_y, ball_radius, outline_paint ) ;

      canvas.drawText( "("   +  ball_center_point_x  +
                       ", "  +  ball_center_point_y  + ")",
                       20, 20, outline_paint ) ;

   }
}