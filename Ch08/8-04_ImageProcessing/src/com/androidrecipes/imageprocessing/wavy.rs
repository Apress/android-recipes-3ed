#pragma version(1)
#pragma rs java_package_name(com.androidrecipes.imageprocessing)

rs_allocation in;

int height;

void root(const uchar4* v_in, uchar4* v_out, const void* usrData, uint32_t x, 
          uint32_t y)
{
   float scaledy = y/(float) height;
   *v_out = *(uchar4*) rsGetElementAt(in, x, (uint32_t) ((scaledy+
                                      sin(scaledy*100)*0.03)*height));
}